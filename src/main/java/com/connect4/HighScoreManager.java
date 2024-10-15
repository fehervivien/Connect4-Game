package com.connect4;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* HighScoreManager osztály:
- Kezeli a high score táblázat betöltését és
  mentését fájlba.
- Megőrzi a legjobb 10 játékost.
- Képes kiírni a legmagasabb pontszámokat.*/

public class HighScoreManager {
    //A legmagasabb pontszámokat tároló lista
    private List<HighScore> highScores;
    //A fájl neve, ahol a pontszámok tárolva vannak
    private static final String FILE_NAME = "highscores.txt";

    public HighScoreManager() {
        highScores = new ArrayList<>();
        betoltHighScores();
    }

    public void betoltHighScores() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                String nev = parts[0];
                int pontszam = Integer.parseInt(parts[1].trim());
                highScores.add(new HighScore(nev, pontszam));
            }
        } catch (IOException e) {
            System.out.println("Hiba a high score fájl betöltésekor: " + e.getMessage());
        }
    }

    public void mentesHighScore(String nev, int pontszam) {
        highScores.add(new HighScore(nev, pontszam));
        Collections.sort(highScores, (a, b) -> b.getPontszam() - a.getPontszam()); // Csökkenő sorrend
        while (highScores.size() > 10) { // Csak az első 10 legjobb
            highScores.remove(highScores.size() - 1);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (HighScore hs : highScores) {
                bw.write(hs.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Hiba a high score fájl mentésekor: " + e.getMessage());
        }
    }

    public void kiirHighScores() {
        System.out.println("High Scores:");
        for (HighScore hs : highScores) {
            System.out.println(hs);
        }
    }
}
