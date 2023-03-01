package File;

import Domain.Carte;
import Domain.Persoana;
import Domain.Rand;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Random;

public class WriteToFile {
    private File file;
    private FileWriter writer;
    private final Persoana persoana;
    private final Carte carte;
    Rand random;

    public WriteToFile(Persoana persoana, Carte carte, Rand random)
    {
        this.persoana = persoana;
        this.carte = carte;
        this.random = random;
    }

    public void genereazaPersoana()
    {
        persoana.setNume(random.genereazaNume());
        persoana.setVarsta(random.genereazaVarsta());
        persoana.setCNP(random.genereazaCNP());
        persoana.setStatus("neimprumutat");
    }

    public void genereazaCarte(ArrayList<Integer> cote)
    {
        carte.setCota(random.genereazaCota(cote));
        carte.setTitlu(random.genereazaTitlu());
        carte.setAutor(random.genereazaAutor());
        carte.setRaft(random.genereazaRaft());
        carte.setStare(random.genereazaStare());
    }

    public void writePersoane() throws IOException {
        file = new File("persoane.txt");
        writer = new FileWriter(file);

        for (int i = 0; i < 500; ++i)
        {
            this.genereazaPersoana();
            writer.write("Nume: " + persoana.getNume() + " Varsta: " + persoana.getVarsta() + " CNP: " + persoana.getCNP() + " Status: " + persoana.getStatus() + '\n');
        }
    }

    public void writeCarti(ArrayList<Integer> cote) throws IOException {
        file = new File("carti.txt");
        writer = new FileWriter(file);

        for (int i = 0; i < 500; ++i)
        {
            this.genereazaCarte(cote);
            writer.write("Cota: " + carte.getCota() + " Titlu: " + carte.getTitlu() + " Autor: " + carte.getAutor() + " Raft: " + carte.getRaft() +
                    " Stare: " + carte.getStare() + " Cititor: " + carte.getCititor() +'\n');
        }
    }


}
