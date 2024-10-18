package com.connect4;
import java.io.*;
import java.util.Arrays;

public class PalyaOlvasKiir {

    private char[][] board;
    private final int rows = 7;
    private final int columns = 6;

    public PalyaOlvasKiir() {
        this.board = new char[rows][columns];
        initializeBoard();
    }

    public PalyaOlvasKiir(String filePath) {
        this.board = new char[rows][columns];
        loadBoard(filePath);
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Pálya betöltése
    public void loadBoard(String filePath) {
        initializeBoard(); // Reset the board to empty before loading
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                // Ellenőrzi, hogy a sor hossza pontosan 6
                if (line.length() == columns) {
                    for (int j = 0; j < columns; j++) {
                        board[row][j] = line.charAt(j);
                    }
                    row++;
                } else {
                    System.out.println("A sor nem megfelelő hosszúságú: " + line.length());
                    System.out.println("Betöltendő fájl útvonala: " + filePath);
                    // Ha bármelyik sor érvénytelen, ne csinálj semmit
                    return; // Stop loading further lines and exit
                }
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl beolvasása közben: " + e.getMessage());
            initializeBoard(); // if there is an error, start with an empty board
        }
    }




    public void saveBoard(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < rows; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < columns; j++) {
                    line.append(board[i][j]);
                }
                // Debug kiírás a táblázat állapotáról
                System.out.println("Mentett sor: " + line.toString());
                bw.write(line.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl írása közben: " + e.getMessage());
            throw new RuntimeException("Hiba történt a pálya mentésekor.", e);
        }
    }







    public char[][] getBoard() {
        return board;
    }

    //Pálya állásának frissítése
    public void updateBoard(int row, char symbol) {
        if (row >= 0 && row < rows) {
            // Az oszlopindexet is módosítsd, ha szükséges
            board[row][0] = symbol; // Az első oszlopba írjuk
        }
    }



}
