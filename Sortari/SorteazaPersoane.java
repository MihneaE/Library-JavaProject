package Sortari;

public enum SorteazaPersoane {
    SORTEAZA_NUME("Nume"),
    SORTEAZA_VARSTA("Varsta");

    SorteazaPersoane(String nume)
    {
        this.nume = nume;
    }

    private String nume;
}
