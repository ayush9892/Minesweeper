package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
       Scanner sc = new Scanner(System.in);

       GroundArea groundArea = new GroundArea();
       DisplayField displayField = new DisplayField();

       System.out.print("How many mines do you want on the field?");
       int mines_no = sc.nextInt();
       BattlePrep battlePrep = new BattlePrep(mines_no);

       groundArea.PlacingMines(mines_no);

       displayField.display(groundArea.fieldCopy);

       battlePrep.battle(groundArea, displayField, mines_no);
       sc.close();
    }
}