package Repository;

import Domain.Carte;
import Domain.Persoana;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class RepoPersoane {
    private Collection<Persoana> cititori;

    public RepoPersoane()
    {
        this.cititori = new ArrayList<>();
    }

    public Collection<Persoana> gettAllCititori()
    {
        return cititori;
    }

    public void adaugaCititor(Persoana persoana)
    {
        this.cititori.add(persoana);
    }

    public void stergeCititor(String nume)
    {
        cititori.removeIf(persoana -> Objects.equals(persoana.getNume(), nume));
    }

    public void modificaCititor(String nume, String nume_nou, Integer varsta_noua, String CNP_nou)
    {
        for (Persoana persoana : cititori)
        {
            if (Objects.equals(persoana.getNume(), nume))
            {
                persoana.setNume(nume_nou);
                persoana.setVarsta(varsta_noua);
                persoana.setCNP(CNP_nou);
            }
        }
    }

}
