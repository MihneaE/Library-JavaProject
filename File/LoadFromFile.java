package File;

import Domain.Carte;
import Domain.Persoana;
import Repository.RepoCarti;
import Repository.RepoPersoane;

import java.io.*;
import java.util.Scanner;

public class LoadFromFile {
    private final RepoCarti repoCarti;
    private final RepoPersoane repoPersoane;

    public LoadFromFile(RepoCarti repoCarti, RepoPersoane repoPersoane)
    {
        this.repoCarti = repoCarti;
        this.repoPersoane = repoPersoane;
    }

    public void loadPersoane()
    {
        try
        {
            Scanner scanner1 = new Scanner(new File("persoane.txt"));
            Scanner scanner2 = new Scanner(new File("persoane.txt"));
            String penultimateLine = null;
            String ultimateLine = null;

            while (scanner1.hasNextLine())
            {
                penultimateLine = ultimateLine;
                ultimateLine = scanner1.nextLine();
            }

            while (scanner2.hasNextLine())
            {
                String line = scanner2.nextLine().strip();
                String[] args = line.split(" ");
                Persoana persoana = new Persoana();
                persoana.setNume(args[1]);
                persoana.setVarsta(Integer.valueOf(args[3]));
                persoana.setCNP(args[5]);
                persoana.setStatus(args[7]);
                repoPersoane.adaugaCititor(persoana);

                if (line.equals(penultimateLine))
                    break;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void loadCarti()
    {
        try
        {
            Scanner scanner1 = new Scanner(new File("carti.txt"));
            Scanner scanner2 = new Scanner(new File("carti.txt"));
            String penultimateLine = null;
            String ultimateLine = null;

            while (scanner1.hasNextLine())
            {
                penultimateLine = ultimateLine;
                ultimateLine = scanner1.nextLine();
            }

            //System.out.println(penultimateLine);
            //System.out.println(ultimateLine);

            while (scanner2.hasNextLine())
            {
                String line = scanner2.nextLine().strip();
                String[] args = line.split(" ");
                Carte carte = new Carte();
                carte.setCota(Integer.valueOf(args[1]));
                carte.setTitlu(args[3]);
                carte.setAutor(args[5]);
                carte.setRaft(Integer.valueOf(args[7]));
                carte.setStare(args[9]);
                repoCarti.adaugaCarte(carte);

                if (line.equals(penultimateLine))
                    break;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}

