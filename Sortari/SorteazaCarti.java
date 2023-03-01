package Sortari;

public enum SorteazaCarti {
    SORTEAZA_COTA("Cota"),
    SORTEAZA_TITLU("Titlu"),
    SORTEAZA_AUTOR("Autor");

    SorteazaCarti(String nume)
    {
        this.nume = nume;
    }

    private String nume;
}
