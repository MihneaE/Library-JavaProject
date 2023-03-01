package Domain;

public class Carte {
    private Integer cota;
    private String titlu;
    private String autor;
    private Integer raft;
    private String stare;
    private Persoana cititor;

    public Carte() {}

    public Carte(Integer cota, String titlu, String autor, Integer raft, String stare, Persoana cititor)
    {
        this.cota = cota;
        this.titlu = titlu;
        this.autor = autor;
        this.raft = raft;
        this.stare = stare;
        this.cititor = cititor;
    }

    public Carte(Integer cota, String titlu, String autor, Integer raft, String stare)
    {
        this.cota = cota;
        this.titlu = titlu;
        this.autor = autor;
        this.raft = raft;
        this.stare = stare;
    }

    public void setCota(Integer cota) {
        this.cota = cota;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setRaft(Integer raft) {
        this.raft = raft;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }

    public void setCititor(Persoana cititor) {
        this.cititor = cititor;
    }

    public Integer getCota() {
        return cota;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getAutor() {
        return autor;
    }

    public Integer getRaft() {
        return raft;
    }

    public String getStare() {
        return stare;
    }

    public Persoana getCititor() {
        return cititor;
    }

    @Override
    public String toString() {
        return "Cota: " + cota + " Titlu: " + titlu  + " Autor: " + autor + " Raft: " + raft + " Stare: " + stare  + " Cititor: " + cititor;
    }
}
