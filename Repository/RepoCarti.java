package Repository;

import Domain.Carte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class RepoCarti {
    private Collection<Carte> carti;

    public RepoCarti()
    {
        this.carti = new ArrayList<>();
    }

    public Collection<Carte> getAllCarti()
    {
        return carti;
    }

    public void adaugaCarte(Carte carte)
    {
        this.carti.add(carte);
    }

    public void stergeCarte(Integer cota)
    {
        carti.removeIf(carte -> Objects.equals(carte.getCota(), cota));
    }

    public void modificaCarte(Integer cota, String titlu_nou, String autor_nou, Integer raft_nou, String stare_noua)
    {
        for (Carte carte : carti)
        {
            if (Objects.equals(carte.getCota(), cota))
            {
                carte.setTitlu(titlu_nou);
                carte.setAutor(autor_nou);
                carte.setRaft(raft_nou);
                carte.setStare(stare_noua);
            }
        }
    }
}
