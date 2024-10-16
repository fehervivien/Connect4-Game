package com.connect4;
import java.io.*;

public class PalyaOlvasasa {

    private char[][] board;
    private final int rows = 6;
    private final int columns = 7;

    public PalyaOlvasasa() {
        this.board = new char[rows][columns];
        initializeBoard();
    }

    public PalyaOlvasasa(String filePath) {
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
    // Pálya betöltése
    public void loadBoard(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < rows) {
                line = line.trim(); // Tisztítsd meg a sort az üres helyektől
                if (line.length() == columns) { // Ellenőrizd, hogy a sor hossza pontosan 7
                    for (int j = 0; j < columns; j++) {
                        board[row][j] = line.charAt(j);
                    }
                    row++;
                } else {
                    System.out.println("A sor nem megfelelő hosszúságú: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl beolvasása közben: " + e.getMessage());
            initializeBoard(); // ha hiba van, üres táblát indít
        }
    }


    // Pálya mentése
    // Pálya mentése
    public void saveBoard(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < rows; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < columns; j++) {
                    line.append(board[i][j]);
                }
                bw.write(line.toString().trim()); // Írd ki a sort, eltávolítva az extra üres helyeket
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl írása közben: " + e.getMessage());
        }
    }


    public char[][] getBoard() {
        return board;
    }

    public void updateBoard(int column, char character) {
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = character;
                break;
            }
        }
    }
}
