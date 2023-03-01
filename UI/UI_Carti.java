package UI;

import Domain.Carte;
import Service.ServiceCarti;
import Service.Service_CartiCititori;
import Threads.Imprumuta_returneaza;
import Threads.Pers_imprumut;
import Threads.Pers_returneaza;
import Validators.Validator;

import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class UI_Carti {
    private ServiceCarti serviceCarti;
    private Service_CartiCititori serviceCartiCititori;
    private Validator validator;
    public UI_Carti(ServiceCarti serviceCarti, Service_CartiCititori serviceCartiCititori, Validator validator)
    {
        this.serviceCarti = serviceCarti;
        this.serviceCartiCititori = serviceCartiCititori;
        this.validator = validator;
    }

    public  void ui_afiseaza_carti()
    {
        for (Carte carte : serviceCarti.getAllCarti())
            System.out.println(carte.toString());
    }

    public  void ui_afiseaza_carti_libere()
    {
        for (Carte carte : serviceCarti.getAllCarti())
            if (Objects.equals(carte.getStare(), "raft"))
                System.out.println(carte);
    }

    public  void ui_afiseaza_carti_imprumutate()
    {
        for (Carte carte : serviceCarti.getAllCarti())
            if (Objects.equals(carte.getStare(), "sala") || Objects.equals(carte.getStare(), "afara"))
                System.out.println(carte);
    }

    public void ui_adauga_carte(Integer[] contor_rafturi)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cota: ");
        Integer cota = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Titlu: ");
        String titlu = scanner.nextLine();
        System.out.println("Autor: ");
        String autor = scanner.nextLine();
        System.out.println("Raft: ");
        Integer raft = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Stare: ");
        String stare = scanner.nextLine();

        if (validator.valideaza_adaugare_cota(cota) && validator.valideaza_raft(raft) && validator.valideaza_stare(stare))
        {
            serviceCarti.adauga_Carte(cota, titlu, autor, raft, stare);
            serviceCartiCititori.increment_total();
            serviceCartiCititori.increment_carti_libere();
            System.out.println("Carte adaugata!");

            switch (stare) {
                case "raft" -> serviceCartiCititori.increment_carti_raft();
                case "sala" -> serviceCartiCititori.increment_carti_sala();
                case "afara" -> serviceCartiCititori.increment_carti_afara();
            }

            for (int i = 0; i < 10; ++i)
            {
                if (raft == i)
                    contor_rafturi[i]++;
            }
        }
        else if (!validator.valideaza_adaugare_cota(cota))
            System.out.println("Cota trebuie sa fie unica!");
        else if (!validator.valideaza_raft(raft))
            System.out.println("Raftul trebuie sa fie intre 0 si 9!");
        else if (!validator.valideaza_stare(stare))
            System.out.println("Starea trebuie sa fie una dintre: raft, sala, afara!");
    }

    public void ui_sterge_carte(Integer[] contor_rafturi)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cota: ");
        Integer cota = scanner.nextInt();

        if (validator.valideaza_cota(cota))
        {
            for (Carte carte : serviceCarti.getAllCarti())
                if (carte.getCota().equals(cota))
                {
                    serviceCartiCititori.decrement_total();

                    if (carte.getCititor() == null)
                        serviceCartiCititori.decrement_carti_libere();
                    else
                        serviceCartiCititori.decrement_carti_imprumutate();

                    switch (carte.getStare()) {
                        case "raft" -> serviceCartiCititori.decrement_carti_raft();
                        case "sala" -> serviceCartiCititori.decrement_carti_sala();
                        case "afara" -> serviceCartiCititori.decrement_carti_afara();
                    }

                    for (int i = 0; i < 10; ++i)
                    {
                        if (carte.getRaft() == i)
                            contor_rafturi[i]--;
                    }
                }

            serviceCarti.sterge_carte(cota);
            System.out.println("Carte stearsa!");
        }
        else
            System.out.println("Cartea nu exista!");
    }

    public void ui_modifica_carte(Integer[] contor_rafturi)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cota: ");
        Integer cota = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Titlu nou: ");
        String titlu_nou = scanner.nextLine();
        System.out.println("Autor nou: ");
        String autor_nou = scanner.nextLine();
        System.out.println("Raft nou: ");
        Integer raft_nou = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Stare noua: ");
        String stare_noua = scanner.nextLine();
        String stare_veche = "";

        if (validator.valideaza_raft(raft_nou) && validator.valideaza_stare(stare_noua))
        {
            for (Carte carte : serviceCarti.getAllCarti())
                if (carte.getCota().equals(cota))
                    stare_veche = carte.getStare();

            serviceCarti.modifica_carte(cota, titlu_nou, autor_nou, raft_nou, stare_noua);
            System.out.println("Carte modificata!");

            if (!stare_noua.equals(stare_veche))
            {
                if (stare_veche.equals("raft") && stare_noua.equals("sala")) {
                    serviceCartiCititori.decrement_carti_raft();
                    serviceCartiCititori.increment_carti_sala();
                } else if (stare_veche.equals("raft") && stare_noua.equals("afara")) {
                    serviceCartiCititori.decrement_carti_raft();
                    serviceCartiCititori.increment_carti_afara();
                } else if (stare_veche.equals("sala") && stare_noua.equals("raft")) {
                    serviceCartiCititori.decrement_carti_sala();
                    serviceCartiCititori.increment_carti_raft();
                } else if (stare_veche.equals("sala") && stare_noua.equals("afara")) {
                    serviceCartiCititori.decrement_carti_sala();
                    serviceCartiCititori.increment_carti_afara();
                } else if (stare_veche.equals("afara") && stare_noua.equals("raft")) {
                    serviceCartiCititori.decrement_carti_afara();
                    serviceCartiCititori.increment_carti_raft();
                } else if (stare_veche.equals("afara") && stare_noua.equals("sala")) {
                    serviceCartiCititori.decrement_carti_afara();
                    serviceCartiCititori.increment_carti_sala();
                }

                /*
                if (raft_nou == 0)
                    contor_rafturi[0]++;
                else if (raft_nou == 1)
                    contor_rafturi[1]++;
                else if (raft_nou == 2)
                    contor_rafturi[2]++;
                else if (raft_nou == 3)
                    contor_rafturi[3]++;
                else if (raft_nou == 4)
                    contor_rafturi[4]++;
                else if (raft_nou == 5)
                    contor_rafturi[5]++;
                else if (raft_nou == 6)
                    contor_rafturi[6]++;
                else if (raft_nou == 7)
                    contor_rafturi[7]++;
                else if (raft_nou == 8)
                    contor_rafturi[8]++;
                else if (raft_nou == 9)
                    contor_rafturi[9]++;

                 */
            }

        }
        else if (!validator.valideaza_raft(raft_nou))
            System.out.println("Raftul trebuie sa fie intre 0 si 9!");
        else if (!validator.valideaza_stare(stare_noua))
            System.out.println("Starea trebuie sa fie una dintre: raft, sala, afara!");
    }

    public void ui_cauta_carte_cota()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cota: ");
        Integer cota = scanner.nextInt();

        if (validator.valideaza_cota(cota))
            System.out.println(serviceCarti.cauta_carte_cota(cota));
        else
            System.out.println("Cota nu exista!");
    }

    public void ui_cauta_carti_titlu()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Titlu: ");
        String titlu = scanner.nextLine();

        if (validator.valideaza_titlu(titlu))
            System.out.println(serviceCarti.cauta_carte_titlu(titlu));
        else
            System.out.println("Titlul nu exista!");
    }

    public void ui_cauta_carti_autor()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Autor: ");
        String autor = scanner.nextLine();

        if (validator.valideaza_autor(autor))
            System.out.println(serviceCarti.cauta_carte_autor(autor));
        else
            System.out.println("Autorul nu exista!");
    }

    public void ui_filtreaza_carti_raft()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Raft: ");
        Integer raft= scanner.nextInt();

        Collection<Carte> carti_filtrate = serviceCarti.filtrare_carti_raft(raft);

        if (validator.valideaza_raft(raft))
        {
            for (Carte carte : carti_filtrate)
                System.out.println(carte.toString());
        }
        else
            System.out.println("Raftul nu exista");
    }

    public void ui_filtreaza_carti_stare()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Stare: ");
        String stare = scanner.nextLine();

        Collection<Carte> carti_filtrate = serviceCarti.filtrare_carti_stare(stare);

        if (validator.valideaza_stare(stare))
        {
            for (Carte carte : carti_filtrate)
                System.out.println(carte.toString());
        }
        else
            System.out.println("Starea nu exista");
    }

    public void ui_imprumuta_carte()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nume: ");
        String nume = scanner.nextLine();
        System.out.println("Titlu: ");
        String titlu = scanner.nextLine();
        System.out.println("Stare noua: ");
        String stare_noua = scanner.nextLine();

        if (validator.valideaza_nume(nume) && validator.valideaza_titlu(titlu) && validator.valideaza_stare(stare_noua) && validator.carte_neimprumutata(titlu) && validator.cititor_neimprumutat(nume))
            System.out.println(serviceCartiCititori.imprumuta_carte(nume, titlu, stare_noua));
        else if (!validator.valideaza_nume(nume))
            System.out.println("Numele nu exista in baza de date, trebuie adaugat!");
        else if (!validator.valideaza_titlu(titlu))
            System.out.println("Cartea nu exista!");
        else if (!validator.valideaza_stare(stare_noua))
            System.out.println("Starea trebuie sa fie una dintre: raft, sala, afara!");
        else if (!validator.carte_neimprumutata(titlu))
            System.out.println("Cartea este imprumutata deja!");
        else if (validator.cititor_neimprumutat(nume))
            System.out.println("Cititorul este imprumutat!");

    }

    public void ui_sortare_carti_cota()
    {
        for (Carte carte : serviceCarti.sorteaza_carti_cota())
            System.out.println(carte.toString());
    }

    public void ui_sortare_carti_titlu()
    {
        for (Carte carte : serviceCarti.sorteaza_carti_titlu())
            System.out.println(carte.toString());
    }

    public void ui_sortare_carti_autor()
    {
        for (Carte carte : serviceCarti.sorteaza_carti_autor())
            System.out.println(carte.toString());
    }

    public void ui_returneaza_carte()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Titlu: ");
        String titlu = scanner.nextLine();

        if (!validator.carte_neimprumutata(titlu) && validator.valideaza_titlu(titlu))
            System.out.println(serviceCartiCititori.returneaza_carte(titlu));
        else if (!validator.valideaza_titlu(titlu))
            System.out.println("Cartea nu exista!");
        else
            System.out.println("Cartea este neimprumutata!");
    }

    public void ui_contorizare_rafturi(Integer[] contor_rafturi)
    {
        for (int i = 0; i < 10; ++i)
        {
            System.out.println("Carti cu numarul raftului " + i  + ": " + contor_rafturi[i]);
        }
    }

    public void ui_contorizare_stari()
    {
        System.out.println("Carti pe raft: " + serviceCartiCititori.getNr_stare_raft());
        System.out.println("Carti in sala: " + serviceCartiCititori.getNr_stare_sala());
        System.out.println("Carti afara: " + serviceCartiCititori.getNr_stare_afara());
    }

    public void ui_contorizare_carti()
    {
        System.out.println("Carti libere: " + serviceCartiCititori.getNr_carti_libere());
        System.out.println("Carti imprumutate: " + serviceCartiCititori.getNr_carti_imprumutate());
        System.out.println("Total carti: " + serviceCartiCititori.getNr_total_carti());
    }

    public void ui_threads(Lock locker, Imprumuta_returneaza imprumutaReturneaza)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Numarul persoanelor care imprumuta de odata: ");
        int numar_imprumuta = scanner.nextInt();
        System.out.println("Numarul persoanelor care returneaza de odata: ");
        int numar_returneaza = scanner.nextInt();

        Thread[] threads = new Thread[10];

        for (int i = 0; i < numar_imprumuta; ++i)
        {
            threads[i] = new Pers_imprumut(imprumutaReturneaza, locker, serviceCartiCititori);
            threads[i].start();
        }

        for (int i = numar_imprumuta; i < numar_imprumuta + numar_returneaza; ++i)
        {
            threads[i] = new Pers_returneaza(imprumutaReturneaza, locker, serviceCartiCititori);
            threads[i].start();
        }

        for (int i = 0; i < numar_imprumuta + numar_returneaza; ++i)
        {
            try
            {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
