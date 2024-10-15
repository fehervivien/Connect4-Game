package com.connect4;

/* HighScore osztály:
- Tárolja a játékos nevét és pontszámát.
- Kínál egy toString() metódust a könnyebb kiíratáshoz.*/

public class HighScore {
    private String nev;
    private int pontszam;

    public HighScore(String nev, int pontszam) {
        this.nev = nev;
        this.pontszam = pontszam;
    }

    public String getNev() {
        return nev;
    }

    public int getPontszam() {
        return pontszam;
    }

    @Override
    public String toString() {
        return nev + ": " + pontszam;
    }
}
