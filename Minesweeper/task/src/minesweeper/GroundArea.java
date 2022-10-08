package minesweeper;

import java.util.Random;

public class GroundArea {
    char[][] field;
    char[][] fieldCopy;

    public GroundArea() {
        field = new char[9][9];
        fieldCopy = new char[9][9];
        for(int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                field[i][j] = '.';
                fieldCopy[i][j] = '.';
            }
        }

    }

    public void PlacingMines(int mNo) {
        Random rnd = new Random();

        while(mNo != 0) {
            int row = rnd.nextInt(0, 9);
            int col = rnd.nextInt(0, 9);
            if(field[row][col] != 'X') {
                field[row][col] = 'X';
                mNo--;
            }
        }
//        field[0][1] = 'X';
//        field[2][2] = 'X';
//        field[8][0] = 'X';
        LookingAround();
    }

    public void LookingAround() {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(field[i][j] != 'X') {
                    int count = MinesCheck(i, j);
                    if(count > 0) {
                        field[i][j] = String.valueOf(count).charAt(0);
                    }
                }
            }
        }
    }

    private int MinesCheck(int i, int j) {
        int count = 0;
        for(int l = -1; l<=1; l++) {
            for(int k = -1; k<=1; k++) {
                try{
                    if(field[i+k][j+l] == 'X') {
                        count++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}

            }
        }
        return count;
    }
}
