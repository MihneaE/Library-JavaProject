package Service;

import Domain.Carte;
import Domain.Persoana;
import File.LoadFromFile;
import Repository.RepoCarti;

import java.util.*;
import java.util.concurrent.Callable;

public class ServiceCarti {
    private RepoCarti repoCarti;
    //private LoadFromFile loadFromFile;

    public ServiceCarti(RepoCarti repoCarti)
    {
        this.repoCarti = repoCarti;
    }

    public Collection<Carte> getAllCarti()
    {
        return repoCarti.getAllCarti();
    }

    public void adauga_Carte(Integer cota, String titlu, String autor, Integer raft, String stare)
    {
        Carte carte = new Carte(cota, titlu, autor, raft, stare);
        repoCarti.adaugaCarte(carte);
    }

    public void sterge_carte(Integer cota)
    {
        repoCarti.stergeCarte(cota);

    }

    public void modifica_carte(Integer cota, String titlu, String autor, Integer raft, String stare)
    {
        repoCarti.modificaCarte(cota, titlu, autor, raft, stare);
    }

    public Carte cauta_carte_cota(Integer cota)
    {
        Carte carte_cota = new Carte();

        for (Carte carte : repoCarti.getAllCarti())
            if (Objects.equals(carte.getCota(), cota))
                carte_cota = carte;

        return  carte_cota;
    }

    public Carte cauta_carte_titlu(String titlu)
    {
        Carte carte_titlu = new Carte();

        for (Carte carte : repoCarti.getAllCarti())
            if (Objects.equals(carte.getTitlu(), titlu))
                carte_titlu = carte;

        return  carte_titlu;
    }

    public Carte cauta_carte_autor(String autor)
    {
        Carte carte_autor = new Carte();

        for (Carte carte : repoCarti.getAllCarti())
            if (Objects.equals(carte.getAutor(), autor))
                carte_autor = carte;

        return carte_autor;
    }

    public Collection<Carte> filtrare_carti_raft(Integer raft)
    {
        Collection<Carte> carti_fart = new ArrayList<>();

        for (Carte carte : repoCarti.getAllCarti())
        {
            if (Objects.equals(carte.getRaft(), raft))
                carti_fart.add(carte);
        }

        return carti_fart;
    }

    public Collection<Carte> filtrare_carti_stare(String stare)
    {
        Collection<Carte> carti_stare = new ArrayList<>();

        for (Carte carte : repoCarti.getAllCarti())
        {
            if (Objects.equals(carte.getStare(), stare))
                carti_stare.add(carte);
        }

        return carti_stare;
    }

    public List<Carte> sorteaza_carti_cota()
    {
        List<Carte> carti_sortate = new ArrayList<>(repoCarti.getAllCarti());

        for (int i = 0; i < carti_sortate.size(); ++i)
            for (int j = i + 1; j < carti_sortate.size(); ++j)
                if (carti_sortate.get(i).getCota() > carti_sortate.get(j).getCota())
                {
                    Carte aux = carti_sortate.get(i);
                    carti_sortate.set(i, carti_sortate.get(j));
                    carti_sortate.set(j, aux);
                }

        return carti_sortate;
    }

    public List<Carte> sorteaza_carti_titlu()
    {
        List<Carte> persoane_sortate = new ArrayList<>(repoCarti.getAllCarti());

        for (int i = 0; i < persoane_sortate.size(); ++i)
            for (int j = i + 1; j < persoane_sortate.size(); ++j)
            {
                char letter1 = persoane_sortate.get(i).getTitlu().charAt(0);
                char letter2 = persoane_sortate.get(j).getTitlu().charAt(0);

                if (letter1 > letter2)
                {
                    Carte aux = persoane_sortate.get(i);
                    persoane_sortate.set(i, persoane_sortate.get(j));
                    persoane_sortate.set(j, aux);
                }
            }

        return persoane_sortate;
    }

    public List<Carte> sorteaza_carti_autor()
    {
        List<Carte> persoane_sortate = new ArrayList<>(repoCarti.getAllCarti());

        for (int i = 0; i < persoane_sortate.size(); ++i)
            for (int j = i + 1; j < persoane_sortate.size(); ++j)
            {
                char letter1 = persoane_sortate.get(i).getAutor().charAt(0);
                char letter2 = persoane_sortate.get(j).getAutor().charAt(0);

                if (letter1 > letter2)
                {
                    Carte aux = persoane_sortate.get(i);
                    persoane_sortate.set(i, persoane_sortate.get(j));
                    persoane_sortate.set(j, aux);
                }
            }

        return persoane_sortate;
    }
}
