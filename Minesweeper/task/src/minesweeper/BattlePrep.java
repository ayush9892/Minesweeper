package minesweeper;

import java.util.Arrays;
import java.util.Scanner;

public class BattlePrep {
    int markedCount = 0;
    int totalMines;
    char matchChar;
    public BattlePrep(int cnt) {
        totalMines = cnt;
    }

    public void battle(GroundArea groundArea, DisplayField displayField, int cnt) {
        Scanner sc = new Scanner(System.in);
        boolean stop = false;

        while(!stop) {
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;
            String claim = sc.next();
            System.out.println();

            switch (claim.toLowerCase()) {
                case "free":
                    if(groundArea.field[y][x] == 'X') {
                        groundArea.fieldCopy[y][x] = 'X';
                        displayField.display(groundArea.field);
                        System.out.println("You stepped on a mine and failed!");
                        stop = true;
                        System.exit(0);
                    } else {
                        //Auto free
                        matchChar = groundArea.field[y][x];
                        try {
                            autoExplored(groundArea, x, y);
                        } catch (StackOverflowError stackE) {}
                        displayField.display(groundArea.fieldCopy);
                    }
                    break;
                case "mine":
                    // Marking
                    if(groundArea.fieldCopy[y][x] == '.') {
                        groundArea.fieldCopy[y][x] = '*';
                        markedCount++;
                        if(groundArea.field[y][x] == 'X') {
                            totalMines--;
                        }
                        displayField.display(groundArea.fieldCopy);
                        // UnMarking
                    } else if (groundArea.fieldCopy[y][x] == '*') {
                        groundArea.fieldCopy[y][x] = '.';
                        markedCount--;
                        if(groundArea.field[y][x] == 'X') {
                            totalMines++;
                        }
                        displayField.display(groundArea.fieldCopy);
                    } else {
                        System.out.println("There is a number here!");
                    }
            }

            int free = freeSpaceCount(groundArea.fieldCopy);
            if((totalMines == 0 && markedCount == cnt) || (free == cnt)) {
                System.out.println("Congratulations! You found all the mines!");
                stop = true;
            }
        }
    }

    private boolean autoExplored(GroundArea gArea, int x, int y) {
        try{
            char curChar = gArea.field[y][x];
            if (curChar == '.') {
                if(gArea.fieldCopy[y][x] == '*') {
                    markedCount--;
                }
                gArea.fieldCopy[y][x] = '/';
                gArea.field[y][x] = '/';

            } else if(curChar != 'X' && curChar != '/') {
                gArea.fieldCopy[y][x] = curChar;
                return false;
            } else {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) { return false; }
        autoExplored(gArea, x, y-1);
        autoExplored(gArea, x, y+1);
        autoExplored(gArea,x-1, y);
        autoExplored(gArea,x+1, y);
        autoExplored(gArea, x+1, y+1);
        autoExplored(gArea, x-1, y-1);
        autoExplored(gArea,x-1, y+1);
        autoExplored(gArea,x+1, y-1);

        return true;
    }


    private int freeSpaceCount(char[][] field) {
        int free = 0;
        for(char[] row: field) {
            for(char col: row) {
                if(col == '.' || col == '*') {
                    free++;
                }
            }
        }
        return free;
    }
}
