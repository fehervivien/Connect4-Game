package com.connect4;
import java.io.*;
import java.util.Scanner;

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

        //Pálya betöltése
        public void loadBoard(String filePath) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                int row = 0;
                while ((line = br.readLine()) != null && row < rows) {
                    for (int j = 0; j < Math.min(columns, line.length()); j++) {
                        board[row][j] = line.charAt(j);
                    }
                    row++;
                }
            } catch (IOException e) {
                System.out.println("Hiba a fájl beolvasása közben: " + e.getMessage());
                initializeBoard(); // ha hiba van, üres táblát indít
            }
        }
        //Pálya mentése
        public void saveBoard(String filePath) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        bw.write(board[i][j]);
                    }
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("Hiba a fájl írása közben: " + e.getMessage());
            }
        }




}
