package Validators;


import Domain.Carte;
import Domain.Persoana;
import Service.ServiceCarti;
import Service.ServicePersoane;

import java.util.Objects;
import java.util.concurrent.Callable;

public class Validator {
    private ServiceCarti serviceCarti;
    private ServicePersoane servicePersoane;
    public Validator(ServiceCarti serviceCarti, ServicePersoane servicePersoane)
    {
        this.serviceCarti = serviceCarti;
        this.servicePersoane = servicePersoane;
    }

    public boolean valideaza_cota(Integer cota)
    {
        int contor = 0;

        for (Carte carte : serviceCarti.getAllCarti())
            if (Objects.equals(cota, carte.getCota()))
                contor++;

        if (contor == 0)
            return false;
        return true;
    }

    public boolean valideaza_titlu(String titlu)
    {
        int contor = 0;

        for (Carte carte : serviceCarti.getAllCarti())
            if (Objects.equals(titlu, carte.getTitlu()))
                contor++;

        if (contor == 0)
            return false;
        return true;
    }

    public boolean valideaza_autor(String autor)
    {
        int contor = 0;

        for (Carte carte : serviceCarti.getAllCarti())
            if (Objects.equals(autor, carte.getAutor()))
                contor++;

        if (contor == 0)
            return false;
        return true;
    }

    public boolean valideaza_nume(String nume)
    {
        int contor = 0;

        for (Persoana persoana : servicePersoane.getALlCititori())
            if (Objects.equals(nume, persoana.getNume()))
                contor++;

        if (contor == 0)
            return false;
        return true;
    }

    public boolean valideaza_CNP(String CNP)
    {
        int contor = 0;

        for (Persoana persoana : servicePersoane.getALlCititori())
            if (Objects.equals(CNP, persoana.getCNP()))
                contor++;

        if (contor == 0)
            return false;
        return true;
    }

    public boolean valideaza_raft(Integer raft)
    {
        if (raft < 0 || raft > 9)
            return false;
        return true;
    }

    public boolean valideaza_stare(String stare)
    {
        if (Objects.equals(stare, "raft"))
            return true;
        if (Objects.equals(stare, "sala"))
            return true;
        if (Objects.equals(stare, "afara"))
            return true;
        return false;
    }

    public boolean valideaza_adaugare_cota(Integer cota)
    {
        int contor = 0;

        for (Carte carte : serviceCarti.getAllCarti())
            if (Objects.equals(cota, carte.getCota()))
                contor++;

        if (contor == 0)
            return true;
        return false;
    }

    public boolean carte_neimprumutata(String titlu)
    {
        boolean ok = false;

        for (Carte carte : serviceCarti.getAllCarti())
            if (carte.getTitlu().equals(titlu))
                if (carte.getCititor() == null)
                    ok = true;

        if (ok)
            return true;
        return false;
    }

    public boolean cititor_neimprumutat(String nume)
    {
        boolean ok = false;

        for (Persoana persoana : servicePersoane.getALlCititori())
            if (persoana.getStatus().equals("neimprumutat"))
                ok = true;

        if (ok)
            return true;
        return false;
    }
}
