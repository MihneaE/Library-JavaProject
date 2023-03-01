package UI;

import Domain.Persoana;
import Service.ServicePersoane;
import Service.Service_CartiCititori;
import Validators.Validator;

import java.util.Scanner;

public class UI_Persoane {
    private ServicePersoane servicePersoane;
    private Service_CartiCititori serviceCartiCititori;
    private Validator validator;

    public UI_Persoane(ServicePersoane servicePersoane, Service_CartiCititori serviceCartiCititori, Validator validator)
    {
        this.servicePersoane = servicePersoane;
        this.serviceCartiCititori = serviceCartiCititori;
        this.validator = validator;
    }

    public void afiseaza_cititori()
    {
        for (Persoana persoana : servicePersoane.getALlCititori())
            System.out.println(persoana.toString());
    }

    public void ui_adauga_cititor()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nume: ");
        String nume = scanner.nextLine();
        System.out.println("Varsta: ");
        Integer varsta = scanner.nextInt();
        scanner.nextLine();
        System.out.println("CNP: ");
        String CNP = scanner.nextLine();

        servicePersoane.adauga_cititor(nume, varsta, CNP);

        System.out.println("Persoana adaugata!");
    }

    public void ui_sterge_cititor()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nume: ");
        String nume = scanner.nextLine();

        if (validator.valideaza_nume(nume))
        {
            servicePersoane.sterge_cititor(nume);
            System.out.println("Persoana stearsa!");
        }
        else
            System.out.println("Persoana nu exista!");
    }

    public void ui_modifica_cititor()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nume: ");
        String nume = scanner.nextLine();
        System.out.println("Nume nou: ");
        String nume_nou = scanner.nextLine();
        System.out.println("Varsta noua: ");
        Integer varsta_noua = scanner.nextInt();
        scanner.nextLine();
        System.out.println("CNP nou: ");
        String CNP_nou = scanner.nextLine();

        servicePersoane.modifica_cititor(nume, nume_nou, varsta_noua, CNP_nou);

        System.out.println("Persoana modificata!");
    }

    public void ui_cauta_persoana_nume()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nume: ");
        String nume = scanner.nextLine();

        if (validator.valideaza_nume(nume))
            System.out.println(servicePersoane.cauta_Cititor_nume(nume));
        else
            System.out.println("Numele nu exista!");
    }

    public void ui_cauta_persoana_CNP()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("CNP: ");
        String CNP = scanner.nextLine();

        if (validator.valideaza_CNP(CNP))
            System.out.println(servicePersoane.cauta_Cititor_CNP(CNP));
        else
            System.out.println("CNP-ul nu exista!");

    }

    public void ui_sortare_persoane_varsta()
    {
        for (Persoana persoana : servicePersoane.sorteaza_persoane_varsta())
            System.out.println(persoana.toString());
    }

    public void ui_sortare_persoane_nume()
    {
        for (Persoana persoana : servicePersoane.sorteaza_persoane_nume())
            System.out.println(persoana.toString());
    }

    public void ui_contorizare_categorii_varsta_imprumutati()
    {
        System.out.println("Persoane cu varsta intre 20 si 40 de ani: " + serviceCartiCititori.getNr_varsta_20_40());
        System.out.println("Persoane cu varsta intre 40 si 60 de ani: " + serviceCartiCititori.getNr_varsta_40_60());
        System.out.println("Persoane cu varsta intre 60 si 80 de ani: " + serviceCartiCititori.getNr_varsta_60_80());
    }
}
