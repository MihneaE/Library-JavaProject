import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Domain.Rand;
import Domain.Carte;
import Domain.Persoana;
import File.LoadFromFile;
import File.WriteToFile;
import Repository.RepoCarti;
import Repository.RepoPersoane;
import Service.ServiceCarti;
import Service.ServicePersoane;
import Service.Service_CartiCititori;
import Threads.Imprumuta_returneaza;
import Threads.Node;
import Threads.Pers_imprumut;
import Threads.Pers_returneaza;
import UI.Console;
import UI.UI_Carti;
import UI.UI_Persoane;
import Validators.Validator;

public class Main {
    private static final ArrayList<Integer> cote = new ArrayList<>();
    private static final Integer[] contor_rafturi = new Integer[12];
    private static final Persoana persoana = new Persoana();
    private static final Carte carte = new Carte();
    private static final Rand random = new Rand();
    private static final RepoPersoane repoPersoane = new RepoPersoane();
    private static final  RepoCarti repoCarti = new RepoCarti();
    private static final Lock locker = new ReentrantLock();

    public static void main(String[] args) throws IOException
    {
        for (int i = 0; i < 12; ++i)
            contor_rafturi[i] = 0;

        WriteToFile writeToFile = new WriteToFile(persoana, carte, random);
        LoadFromFile loadFromFile = new LoadFromFile(repoCarti, repoPersoane);
        ServicePersoane servicePersoane = new ServicePersoane(repoPersoane);
        ServiceCarti serviceCarti = new ServiceCarti(repoCarti);
        Service_CartiCititori serviceCartiCititori = new Service_CartiCititori(serviceCarti, servicePersoane, contor_rafturi);
        Validator validator = new Validator(serviceCarti, servicePersoane);
        UI_Persoane ui_persoane = new UI_Persoane(servicePersoane, serviceCartiCititori, validator);
        UI_Carti ui_carti = new UI_Carti(serviceCarti, serviceCartiCititori, validator);

        Imprumuta_returneaza imprumutaReturneaza = new Imprumuta_returneaza(serviceCarti, servicePersoane, serviceCartiCititori);

        Console console = new Console(ui_carti, ui_persoane, serviceCartiCititori, contor_rafturi, locker, imprumutaReturneaza);

        writeToFile.writePersoane();
        random.shuffleCote(cote);
        writeToFile.writeCarti(cote);

        loadFromFile.loadPersoane();
        loadFromFile.loadCarti();

        serviceCartiCititori.seteaza_imprumuturi();
        serviceCartiCititori.Init();

        console.run();
    }

}