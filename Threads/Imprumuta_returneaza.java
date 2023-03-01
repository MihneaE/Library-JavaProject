package Threads;

import Domain.Carte;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Domain.Persoana;
import Service.ServiceCarti;
import Service.ServicePersoane;
import Service.Service_CartiCititori;

public class Imprumuta_returneaza {
    private final ServiceCarti serviceCarti;
    private final ServicePersoane servicePersoane;
    private final Service_CartiCititori serviceCartiCititori;
    private Random rand = new Random();

    public Imprumuta_returneaza(ServiceCarti serviceCarti, ServicePersoane servicePersoane, Service_CartiCititori serviceCartiCititori)
    {
        this.serviceCarti = serviceCarti;
        this.servicePersoane = servicePersoane;
        this.serviceCartiCititori = serviceCartiCititori;
    }

    public void imprumuta_carte()
    {
        List<Carte> lista_carti_libere = new ArrayList<>();
        List<Persoana> lista_persoane_neimprumutate = new ArrayList<>();
        Carte randomCarte;
        Persoana randomPersoana;

        for (Carte carte : serviceCarti.getAllCarti())
            if (carte.getCititor() == null)
                lista_carti_libere.add(carte);

        for (Persoana persoana : servicePersoane.getALlCititori())
            if (persoana.getStatus().equals("neimprumutat"))
                lista_persoane_neimprumutate.add(persoana);


        int randomIndex = rand.nextInt(lista_carti_libere.size());
        randomCarte = lista_carti_libere.get(randomIndex);

        int randomIndex2 = rand.nextInt(lista_persoane_neimprumutate.size());
        randomPersoana = lista_persoane_neimprumutate.get(randomIndex2);

        randomCarte.setCititor(randomPersoana);

        for (Carte carte : serviceCarti.getAllCarti())
            if (carte == randomCarte)
                carte.setCititor(randomPersoana);

        for (Persoana persoana : servicePersoane.getALlCititori())
            if (persoana == randomPersoana)
                persoana.setStatus("imprumutat");

        serviceCartiCititori.increment_carti_imprumtuate();
        serviceCartiCititori.decrement_carti_libere();

        Node node = new Node(randomCarte.getCota(), randomPersoana.getCNP());

        System.out.println(node.toString_1());
    }

    public void returneaza_carte()
    {
        List<Carte> lista_carti_imprumutate = new ArrayList<>();
        Carte randomCarte;
        Persoana persoana_impr;

        for (Carte carte : serviceCarti.getAllCarti())
            if (carte.getCititor() != null)
                lista_carti_imprumutate.add(carte);

        int randomIndex = rand.nextInt(lista_carti_imprumutate.size());
        randomCarte = lista_carti_imprumutate.get(randomIndex);

        persoana_impr = randomCarte.getCititor();
        randomCarte.setCititor(null);

        for (Carte carte : serviceCarti.getAllCarti())
            if (carte == randomCarte)
                carte.setCititor(null);

        for (Persoana persoana : servicePersoane.getALlCititori())
            if (persoana == persoana_impr)
                persoana.setStatus("neimprumutat");

        serviceCartiCititori.increment_carti_libere();
        serviceCartiCititori.decrement_carti_imprumutate();

        Node node = new Node(randomCarte.getCota(), persoana_impr.getCNP());

        System.out.println(node.toString_2());
    }
}
