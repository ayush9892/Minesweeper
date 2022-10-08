package minesweeper;

public class DisplayField {
    public void display(char[][] field) {
        System.out.print(" |");
        for(int i=1; i<=9; i++) {
            System.out.print(i);
        }
        System.out.println("|");
        border();

        int rw =1;
        for(char[] row: field) {
            System.out.print(rw + "|");
            for(char col: row) {
                System.out.print(col);
            }
            System.out.println("|");
            rw++;
        }
        border();
    }

    private void border() {
        for(int i=0; i<10; i++) {
            if(i==1) {
                System.out.print("|");
            }
            System.out.print("-");
        }
        System.out.println("|");
    }
}
