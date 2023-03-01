package Service;

import Domain.Carte;
import Domain.Persoana;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.LongAdder;

public class Service_CartiCititori {
    private ServiceCarti serviceCarti;
    private ServicePersoane servicePersoane;
    private Integer nr_carti_imprumutate;
    private Integer nr_carti_libere;
    private Integer nr_total_carti;
    private Integer nr_stare_raft;
    private Integer nr_stare_sala;
    private Integer nr_stare_afara;
    private Integer nr_varsta_20_40;
    private Integer nr_varsta_40_60;
    private Integer nr_varsta_60_80;
    private Integer[] contor_rafturi;

    public Service_CartiCititori(ServiceCarti serviceCarti, ServicePersoane servicePersoane, Integer[] contor_rafturi)
    {
        this.serviceCarti = serviceCarti;
        this.servicePersoane = servicePersoane;
        this.nr_carti_imprumutate = 0;
        this.nr_carti_libere = 0;
        this.nr_total_carti = 0;
        this.nr_stare_raft = 0;
        this.nr_stare_sala = 0;
        this.nr_stare_afara = 0;
        this.nr_varsta_20_40 = 0;
        this.nr_varsta_40_60 = 0;
        this.nr_varsta_60_80 = 0;
        this.contor_rafturi = contor_rafturi;
    }

    public Carte imprumuta_carte(String nume_cititor, String titlu_carte, String stare_noua)
    {
        Persoana persoana_imprumut = new Persoana();
        Carte carte_imprumutata = new Carte();

        for (Persoana persoana : servicePersoane.getALlCititori())
            if (Objects.equals(persoana.getNume(), nume_cititor))
            {
                persoana_imprumut = persoana;
                persoana.setStatus("imprumutat");
            }

        for (Carte carte : serviceCarti.getAllCarti())
            if (Objects.equals(carte.getTitlu(), titlu_carte))
            {
                carte.setCititor(persoana_imprumut);
                carte.setStare(stare_noua);
                carte_imprumutata = carte;
            }

        this.decrement_carti_raft();
        if (Objects.equals(stare_noua, "sala"))
            this.increment_carti_sala();
        else if (Objects.equals(stare_noua, "afara"))
            this.increment_carti_afara();

        if (persoana_imprumut.getVarsta() > 19 && persoana_imprumut.getVarsta() < 40)
            this.increment_varsta_20_40();
        else if (persoana_imprumut.getVarsta() > 39 && persoana_imprumut.getVarsta() < 60)
            this.increment_varsta_40_60();
        else if (persoana_imprumut.getVarsta() > 59 && persoana_imprumut.getVarsta() < 81)
            this.increment_varsta_60_80();

        this.increment_carti_imprumtuate();
        this.decrement_carti_libere();

        return carte_imprumutata;
    }

    public void seteaza_imprumuturi()
    {
        List<Persoana> cititori = new ArrayList<>(servicePersoane.getALlCititori());

        for (Carte carte : serviceCarti.getAllCarti())
            if (carte.getStare().equals("sala") || carte.getStare().equals("afara"))
            {
                for (int i = nr_carti_imprumutate; i < cititori.size(); ++i)
                {
                    carte.setCititor(cititori.get(i));
                    cititori.get(i).setStatus("imprumutat");
                    this.increment_carti_imprumtuate();
                    break;
                }
            }
    }

    public Carte returneaza_carte(String titlu_carte)
    {
        Carte carte_returnata = new Carte();
        Persoana persoana_returneaza = new Persoana();
        String stare_veche = "";

        for (Carte carte : serviceCarti.getAllCarti())
        {
            if (Objects.equals(carte.getTitlu(), titlu_carte))
            {
                for (Persoana persoana : servicePersoane.getALlCititori())
                    if (persoana.equals(carte.getCititor()))
                    {
                        persoana.setStatus("neimprumutat");
                        persoana_returneaza = persoana;
                    }

                stare_veche = carte.getStare();
                carte.setCititor(null);
                carte.setStare("raft");
                carte_returnata = carte;
            }
        }

        this.increment_carti_raft();
        if (Objects.equals(stare_veche, "sala"))
            this.decrement_carti_sala();
        else if (Objects.equals(stare_veche, "afara"))
            this.decrement_carti_afara();

        if (persoana_returneaza.getVarsta() > 19 && persoana_returneaza.getVarsta() < 40)
            this.decrement_varsta_20_40();
        else if (persoana_returneaza.getVarsta() > 39 && persoana_returneaza.getVarsta() < 60)
            this.decrement_varsta_40_60();
        else if (persoana_returneaza.getVarsta() > 59 && persoana_returneaza.getVarsta() < 81)
            this.decrement_varsta_60_80();

        this.decrement_carti_imprumutate();
        this.increment_carti_libere();

        return carte_returnata;
    }

    public void increment_carti_imprumtuate()
    {
        this.nr_carti_imprumutate++;
    }

    public void decrement_carti_imprumutate()
    {
        this.nr_carti_imprumutate--;
    }

    public void increment_carti_libere()
    {
        this.nr_carti_libere++;
    }

    public void decrement_carti_libere()
    {
        this.nr_carti_libere--;
    }

    public Integer getNr_carti_imprumutate()
    {
        return nr_carti_imprumutate;
    }

    public void contorizeaza_carti_libere()
    {
        for (Carte carte : serviceCarti.getAllCarti())
            if (carte.getCititor() == null)
                this.nr_carti_libere++;
    }
    public Integer getNr_carti_libere()
    {
        return nr_carti_libere;
    }

    public Integer getNr_total_carti()
    {
        return this.getNr_carti_imprumutate() + this.getNr_carti_libere();
    }

    public void increment_total()
    {
        this.nr_total_carti++;
    }

    public void decrement_total()
    {
        this.nr_total_carti--;
    }

    public void increment_carti_raft()
    {
        this.nr_stare_raft++;
    }

    public void decrement_carti_raft()
    {
        this.nr_stare_raft--;
    }

    public void increment_carti_sala()
    {
        this.nr_stare_sala++;
    }

    public void decrement_carti_sala()
    {
        this.nr_stare_sala--;
    }

    public void increment_carti_afara()
    {
        this.nr_stare_afara++;
    }

    public void decrement_carti_afara()
    {
        this.nr_stare_afara--;
    }

    public void contorizeaza_rafturi()
    {
        for (Carte carte : serviceCarti.getAllCarti())
        {
            if (carte.getRaft() == 0)
                this.contor_rafturi[0]++;
            else if (carte.getRaft() == 1)
                this.contor_rafturi[1]++;
            else if (carte.getRaft() == 2)
                this.contor_rafturi[2]++;
            else if (carte.getRaft() == 3)
                this.contor_rafturi[3]++;
            else if (carte.getRaft() == 4)
                this.contor_rafturi[4]++;
            else if (carte.getRaft() == 5)
                this.contor_rafturi[5]++;
            else if (carte.getRaft() == 6)
                this.contor_rafturi[6]++;
            else if (carte.getRaft() == 7)
                this.contor_rafturi[7]++;
            else if (carte.getRaft() == 8)
                this.contor_rafturi[8]++;
            else if (carte.getRaft() == 9)
                this.contor_rafturi[9]++;
        }
    }

    public void contorizeaza_stari()
    {
        for (Carte carte : serviceCarti.getAllCarti())
        {
            if (Objects.equals(carte.getStare(), "raft"))
                this.nr_stare_raft++;
            else if (Objects.equals(carte.getStare(), "sala"))
                this.nr_stare_sala++;
            else if (Objects.equals(carte.getStare(), "afara"))
                this.nr_stare_afara++;
        }
    }

    public Integer getNr_stare_raft()
    {
        return nr_stare_raft;
    }

    public Integer getNr_stare_sala()
    {
        return nr_stare_sala;
    }

    public Integer getNr_stare_afara()
    {
        return nr_stare_afara;
    }

    public void contorizeaza_categorii_varsta_imprumutati()
    {
        for (Persoana persoana : servicePersoane.getALlCititori())
            if (persoana.getStatus().equals("imprumutat"))
            {
                if (persoana.getVarsta() > 19 && persoana.getVarsta() < 40)
                    this.nr_varsta_20_40++;
                else if (persoana.getVarsta() > 39 && persoana.getVarsta() < 60)
                    this.nr_varsta_40_60++;
                else if (persoana.getVarsta() > 59 && persoana.getVarsta() < 81)
                    this.nr_varsta_60_80++;
            }
    }

    public Integer getNr_varsta_20_40()
    {
        return nr_varsta_20_40;
    }

    public Integer getNr_varsta_40_60()
    {
        return nr_varsta_40_60;
    }

    public Integer getNr_varsta_60_80()
    {
        return nr_varsta_60_80;
    }

    public void increment_varsta_20_40()
    {
        this.nr_varsta_20_40++;
    }

    public void decrement_varsta_20_40()
    {
        this.nr_varsta_20_40--;
    }

    public void increment_varsta_40_60()
    {
        this.nr_varsta_40_60++;
    }

    public void decrement_varsta_40_60()
    {
        this.nr_varsta_40_60--;
    }

    public void increment_varsta_60_80()
    {
        this.nr_varsta_60_80++;
    }

    public void decrement_varsta_60_80()
    {
        this.nr_varsta_60_80--;
    }

    public void Init()
    {
        this.nr_total_carti = this.getNr_total_carti();
        this.contorizeaza_rafturi();
        this.contorizeaza_carti_libere();
        this.contorizeaza_stari();
        this.contorizeaza_categorii_varsta_imprumutati();
    }
}
