package UI;

import Service.Service_CartiCititori;
import Sortari.SorteazaCarti;
import Sortari.SorteazaPersoane;
import Threads.Imprumuta_returneaza;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class Console {
    private final UI_Carti ui_carti;
    private final UI_Persoane ui_persoane;
    private final Service_CartiCititori serviceCartiCititori;
    private Integer[] contor_rafturi;
    private final Lock locker;
    private final Imprumuta_returneaza imprumutaReturneaza;

    public Console(UI_Carti ui_carti, UI_Persoane ui_persoane, Service_CartiCititori serviceCartiCititori, Integer[] contor_rafturi, Lock locker, Imprumuta_returneaza imprumutaReturneaza)
    {
        this.ui_carti = ui_carti;
        this.ui_persoane = ui_persoane;
        this.serviceCartiCititori = serviceCartiCititori;
        this.contor_rafturi = contor_rafturi;
        this.locker = locker;
        this.imprumutaReturneaza = imprumutaReturneaza;
    }

    public void Meniu()
    {
        System.out.println("0. Iesi ");
        System.out.println("1. Inapoi");
        System.out.println("2. Afiseaza");
        System.out.println("3. Adauga");
        System.out.println("4. Sterge ");
        System.out.println("5. Modifica");
        System.out.println("6. Cauta");
        System.out.println("7. Filtreaza");
        System.out.println("8. Sorteaza");
        System.out.println("9. Contorizarea");
        System.out.println("10. Imprumuta o carte");
        System.out.println("11. Returneaza carte");
        System.out.println("12. Threads");
    }

    public void meniu_afiseaza()
    {
        System.out.println("0. Inapoi");
        System.out.println("1. Afiseaza carti");
        System.out.println("2. Afiseaza cititori");
    }

    public void meniu_adauga()
    {
        System.out.println("0. Inapoi");
        System.out.println("1. Adauga carte");
        System.out.println("2. Adauga cititor");
    }

    public void meniu_sterge()
    {
        System.out.println("0. Inapoi");
        System.out.println("1. Sterge carte");
        System.out.println("2. Sterge cititor");
    }

    public void meniu_modifica()
    {
        System.out.println("0. Inapoi");
        System.out.println("1. Modifica carte");
        System.out.println("2. Modifica cititor");
    }

    public void meniu_cauta()
    {
        System.out.println("0. Inapoi");
        System.out.println("1. Cauta carte");
        System.out.println("2. Cauta cititor");
    }

    public void meniu_filtreaza()
    {
        System.out.println("0. Inapoi");
        System.out.println("1. Filtreaza carti dupa raft");
        System.out.println("2. Filtreaza carti dupa stare");
    }

    public void meniu_sorteaza()
    {
        System.out.println("0. Inapoi");
        System.out.println("1. Sorteaza carti");
        System.out.println("2. Sorteaza cititori");
    }

    public void meniu_contorizari()
    {
        System.out.println("0. Inapoi");
        System.out.println("1. Contorizarea cartilor");
        System.out.println("2. Contorizarea rafturilor");
        System.out.println("3. Contorizarea starilor");
        System.out.println("4. Contorizarea varstelor cititorilor imprumutati");
    }

    public void run() {
        this.Meniu();

        int cmd;
        boolean status = true;

        while (status)
        {
            try {

                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduceti comanda: ");
                cmd = scanner.nextInt();

                if (cmd == 0) {
                    status = false;
                } else if (cmd == 1) {
                    this.Meniu();
                } else if (cmd == 2) {
                    this.meniu_afiseaza();

                    int command;
                    System.out.println("Introduceti comanda: ");
                    command = scanner.nextInt();

                    if (command == 1) {
                        System.out.println("1. Afiseaza toate cartile");
                        System.out.println("2. Afiseaza cartile libere");
                        System.out.println("3. Afiseaza cartile imprumutate");

                        int command2;
                        System.out.println("Introduceti comanda: ");
                        command2 = scanner.nextInt();

                        if (command2 == 1) {
                            ui_carti.ui_afiseaza_carti();
                        } else if (command2 == 2) {
                            ui_carti.ui_afiseaza_carti_libere();
                        } else if (command2 == 3) {
                            ui_carti.ui_afiseaza_carti_imprumutate();
                        }
                        else {
                            System.out.println("Comanda inexistenta!");
                        }
                    } else if (command == 2) {
                        ui_persoane.afiseaza_cititori();
                    } else if (command == 0) {
                        System.out.println("Inapoi\n");
                    }else {
                        System.out.println("Comanda inexistenta!");
                    }

                    this.Meniu();
                } else if (cmd == 3) {
                    this.meniu_adauga();

                    int command;
                    System.out.println("Introduceti comanda: ");
                    command = scanner.nextInt();

                    if (command == 1) {
                        ui_carti.ui_adauga_carte(this.contor_rafturi);
                    } else if (command == 2) {
                        ui_persoane.ui_adauga_cititor();
                    } else if (command == 0) {
                        System.out.println("Inapoi\n");
                    } else {
                        System.out.println("Comanda inexistenta!");
                    }

                    this.Meniu();
                } else if (cmd == 4) {
                    this.meniu_sterge();

                    int command;
                    System.out.println("Introduceti comanda: ");
                    command = scanner.nextInt();

                    if (command == 1) {
                        ui_carti.ui_sterge_carte(contor_rafturi);
                    } else if (command == 2) {
                        ui_persoane.ui_sterge_cititor();
                    } else if (command == 0) {
                        System.out.println("Inapoi\n");
                    } else {
                        System.out.println("Comanda inexistenta!");
                    }

                    this.Meniu();
                } else if (cmd == 5) {
                    this.meniu_modifica();

                    int command;
                    System.out.println("Introduceti comanda: ");
                    command = scanner.nextInt();

                    if (command == 1) {
                        ui_carti.ui_modifica_carte(contor_rafturi);
                    } else if (command == 2) {
                        ui_persoane.ui_modifica_cititor();
                    } else if (command == 0) {
                        System.out.println("Inapoi\n");
                    } else {
                        System.out.println("Comanda inexistenta!");
                    }

                    this.Meniu();
                } else if (cmd == 6) {
                    this.meniu_cauta();

                    int command;
                    System.out.println("Introduceti comanda: ");
                    command = scanner.nextInt();

                    if (command == 1) {
                        System.out.println("1. Cauta carte dupa cota");
                        System.out.println("2. Cauta carte dupa titlu");
                        System.out.println("3. Cauta carte dupa autor");

                        int command2;
                        System.out.println("Introduceti comanda: ");
                        command2 = scanner.nextInt();

                        if (command2 == 1) {
                            ui_carti.ui_cauta_carte_cota();
                        } else if (command2 == 2) {
                            ui_carti.ui_cauta_carti_titlu();
                        } else if (command2 == 3) {
                            ui_carti.ui_cauta_carti_autor();
                        } else {
                            System.out.println("Comanda inexistenta!");
                        }
                    } else if (command == 2) {
                        System.out.println("1. Cauta cititor dupa nume");
                        System.out.println("2. Cauta cititor dupa CNP");

                        int command2;
                        System.out.println("Introduceti comanda: ");
                        command2 = scanner.nextInt();

                        if (command2 == 1) {
                            ui_persoane.ui_cauta_persoana_nume();
                        } else if (command2 == 2) {
                            ui_persoane.ui_cauta_persoana_CNP();
                        } else {
                            System.out.println("Comanda inexistenta!");
                        }
                    } else if (command == 0) {
                        System.out.println("Inapoi\n");
                    } else {
                        System.out.println("Comanda inexistenta!");
                    }

                    this.Meniu();
                } else if (cmd == 7) {
                    this.meniu_filtreaza();

                    int command;
                    System.out.println("Introduceti comanda: ");
                    command = scanner.nextInt();

                    if (command == 1) {
                        ui_carti.ui_filtreaza_carti_raft();
                    } else if (command == 2) {
                        ui_carti.ui_filtreaza_carti_stare();
                    } else if (command == 0) {
                        System.out.println("Inapoi\n");
                    } else {
                        System.out.println("Comanda inexistenta!");
                    }

                    this.Meniu();
                } else if (cmd == 8) {
                    this.meniu_sorteaza();

                    int command;
                    System.out.println("Introduceti comanda: ");
                    command = scanner.nextInt();

                    SorteazaCarti tip_sortare_carti = null;
                    SorteazaPersoane tip_sortare_persoane = null;

                    if (command == 1) {
                        System.out.println("1. Sorteaza carti dupa cota");
                        System.out.println("2. Sorteaza carti dupa titlu");
                        System.out.println("3. Sorteaza carti dupa autor");


                        int command2;
                        System.out.println("Introduceti comanda: ");
                        command2 = scanner.nextInt();

                        if (command2 == 1)
                            tip_sortare_carti = SorteazaCarti.SORTEAZA_COTA;
                        else if (command2 == 2)
                            tip_sortare_carti = SorteazaCarti.SORTEAZA_TITLU;
                        else if (command2 == 3)
                            tip_sortare_carti = SorteazaCarti.SORTEAZA_AUTOR;


                        if (tip_sortare_carti == SorteazaCarti.SORTEAZA_COTA) {
                            ui_carti.ui_sortare_carti_cota();
                        } else if (tip_sortare_carti == SorteazaCarti.SORTEAZA_TITLU) {
                            ui_carti.ui_sortare_carti_titlu();
                        } else if (tip_sortare_carti == SorteazaCarti.SORTEAZA_AUTOR) {
                            ui_carti.ui_sortare_carti_autor();
                        } else {
                            System.out.println("Comanda inexistenta!");
                        }
                    } else if (command == 2) {
                        System.out.println("1. Sorteaza cititori dupa nume");
                        System.out.println("2. Sorteaza cititori dupa varsta");

                        int command2;
                        System.out.println("Introduceti comanda: ");
                        command2 = scanner.nextInt();

                        if (command2 == 1)
                            tip_sortare_persoane = SorteazaPersoane.SORTEAZA_NUME;
                        else if (command2 == 2)
                            tip_sortare_persoane = SorteazaPersoane.SORTEAZA_VARSTA;


                        if (tip_sortare_persoane == SorteazaPersoane.SORTEAZA_NUME) {
                            ui_persoane.ui_sortare_persoane_nume();
                        } else if (tip_sortare_persoane == SorteazaPersoane.SORTEAZA_VARSTA) {
                            ui_persoane.ui_sortare_persoane_varsta();
                        } else {
                            System.out.println("Comanda inexistenta!");
                        }
                    } else if (command == 0) {
                        System.out.println("Inapoi\n");
                    } else {
                        System.out.println("Comanda inexistenta!");
                    }

                    this.Meniu();
                } else if (cmd == 9) {
                    this.meniu_contorizari();

                    int command;
                    System.out.println("Introduceti comanda: ");
                    command = scanner.nextInt();


                    if (command == 1) {
                        ui_carti.ui_contorizare_carti();
                    } else if (command == 2) {
                        ui_carti.ui_contorizare_rafturi(this.contor_rafturi);
                    } else if (command == 3) {
                        ui_carti.ui_contorizare_stari();
                    } else if (command == 4) {
                        ui_persoane.ui_contorizare_categorii_varsta_imprumutati();
                    } else if (command == 0) {
                        System.out.println("Inapoi\n");
                    } else {
                        System.out.println("Comanda inexistenta!");
                    }

                    this.Meniu();
                } else if (cmd == 10) {
                    ui_carti.ui_imprumuta_carte();
                    this.Meniu();
                } else if (cmd == 11) {
                    ui_carti.ui_returneaza_carte();
                    this.Meniu();
                }
                else if (cmd == 12)
                {
                    ui_carti.ui_threads(locker, imprumutaReturneaza);
                    this.Meniu();
                }
                else
                {
                    System.out.println("Comanda inexistenta!");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Formatul este gresit!");
            }
        }
    }

}
