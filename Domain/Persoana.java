package Domain;

public class Persoana {
    private String nume;
    private Integer varsta;
    private String CNP;
    private String status;

    public Persoana() {}
    public Persoana(String nume, Integer varsta, String CNP)
    {
        this.nume = nume;
        this.varsta = varsta;
        this.CNP = CNP;
    }

    public void setNume(String nume)
    {
        this.nume = nume;
    }

    public void setVarsta(Integer varsta)
    {
        this.varsta = varsta;
    }

    public void setStatus(String status) { this.status = status; }

    public void setCNP(String CNP)
    {
        this.CNP = CNP;
    }

    public String getNume()
    {
        return nume;
    }

    public Integer getVarsta()
    {
        return varsta;
    }

    public String getCNP()
    {
        return CNP;
    }

    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Nume: " + nume + " Varsta: " + varsta + " CNP: " + CNP + " Status: " + status + '\n';
    }
}
