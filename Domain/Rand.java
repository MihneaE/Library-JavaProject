package Domain;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Rand {
    private int index;
    public Rand()
    {
        this.index = 0;
    }

    public String genereazaNume()
    {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
        StringBuilder nume = new StringBuilder();

        for (int i = 0; i < 5; ++i)
        {
            int index = (int)(alpha.length() * Math.random());
            nume.append(alpha.charAt(index));
        }

        return nume.toString();
    }

    public Integer genereazaVarsta()
    {
        Random rand = new Random();
        Integer low = 20;
        Integer high = 80;

        return rand.nextInt(high - low) + low;
    }

    public String genereazaCNP()
    {
        String numeric = "0123456789";
        StringBuilder CNP = new StringBuilder();

        for (int i = 0; i < 13; ++i)
        {
            int index = (int)(numeric.length() * Math.random());
            CNP.append(numeric.charAt(index));
        }

        return CNP.toString();
    }

    public void shuffleCote(ArrayList<Integer> cote)
    {
        for (int i = 1; i <= 501; ++i)
            cote.add(i);

        Collections.shuffle(cote);
    }

    public Integer genereazaCota(ArrayList<Integer> cote)
    {
        index++;

        return cote.get(index);
    }

    public String genereazaTitlu()
    {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
        StringBuilder titlu = new StringBuilder();

        for (int i = 0; i < 5; ++i)
        {
            int index = (int)(alpha.length() * Math.random());
            titlu.append(alpha.charAt(index));
        }

        return titlu.toString();
    }

    public String genereazaAutor()
    {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
        StringBuilder autor = new StringBuilder();

        for (int i = 0; i < 10; ++i)
        {
            int index = (int)(alpha.length() * Math.random());
            autor.append(alpha.charAt(index));
        }

        return autor.toString();
    }

    public Integer genereazaRaft()
    {
        Random rand = new Random();
        Integer raft = rand.nextInt(10);

        return raft;
    }

    public String genereazaStare()
    {
        final String[] stari = {"raft", "afara", "sala"};
        Random rand = new Random();
        int stare = rand.nextInt(stari.length);

        return stari[stare];
    }
}
