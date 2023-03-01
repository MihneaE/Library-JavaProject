package Service;

import Domain.Carte;
import Domain.Persoana;
import Repository.RepoPersoane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ServicePersoane {
    private RepoPersoane repoPersoane;

    public ServicePersoane(RepoPersoane repoPersoane)
    {
        this.repoPersoane = repoPersoane;
    }

    public Collection<Persoana> getALlCititori()
    {
        return repoPersoane.gettAllCititori();
    }

    public void adauga_cititor(String nume, Integer vartsta, String CNP)
    {
        Persoana persoana = new Persoana(nume, vartsta, CNP);
        persoana.setStatus("neimprumutat");
        repoPersoane.adaugaCititor(persoana);
    }

    public void sterge_cititor(String nume)
    {
        repoPersoane.stergeCititor(nume);
    }

    public void modifica_cititor(String nume, String nume_nou, Integer varsta_noua, String CNP_nou)
    {
        repoPersoane.modificaCititor(nume, nume_nou, varsta_noua, CNP_nou);
    }

    public Persoana cauta_Cititor_nume(String nume)
    {
        Persoana persoana_nume = new Persoana();

        for (Persoana persoana : repoPersoane.gettAllCititori())
            if (Objects.equals(persoana.getNume(), nume))
                persoana_nume = persoana;

        return  persoana_nume;
    }

    public Persoana cauta_Cititor_CNP(String CNP)
    {
        Persoana persoana_CNP = new Persoana();

        for (Persoana persoana : repoPersoane.gettAllCititori())
            if (Objects.equals(persoana.getCNP(), CNP))
                persoana_CNP = persoana;

        return  persoana_CNP;
    }

    public List<Persoana> sorteaza_persoane_varsta()
    {
        List<Persoana> persoane_sortate = new ArrayList<>(repoPersoane.gettAllCititori());

        for (int i = 0; i < persoane_sortate.size(); ++i)
            for (int j = i + 1; j < persoane_sortate.size(); ++j)
                if (persoane_sortate.get(i).getVarsta() > persoane_sortate.get(j).getVarsta())
                {
                    Persoana aux = persoane_sortate.get(i);
                    persoane_sortate.set(i, persoane_sortate.get(j));
                    persoane_sortate.set(j, aux);
                }

        return persoane_sortate;
    }

    public List<Persoana> sorteaza_persoane_nume()
    {
        List<Persoana> persoane_sortate = new ArrayList<>(repoPersoane.gettAllCititori());

        for (int i = 0; i < persoane_sortate.size(); ++i)
            for (int j = i + 1; j < persoane_sortate.size(); ++j)
            {
                char letter1 = persoane_sortate.get(i).getNume().charAt(0);
                char letter2 = persoane_sortate.get(j).getNume().charAt(0);

                if (letter1 > letter2)
                {
                    Persoana aux = persoane_sortate.get(i);
                    persoane_sortate.set(i, persoane_sortate.get(j));
                    persoane_sortate.set(j, aux);
                }
            }

        return persoane_sortate;
    }

}
