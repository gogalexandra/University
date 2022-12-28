package Repository;

import javax.swing.*;
import java.util.ArrayList;

public class SlidingPuzzle {
    private int[][] puzzle;
    private int[][] finalForm;


    //CONSTRUCTORS
    public SlidingPuzzle(){
        this.puzzle = new int[3][3];
        this.finalForm = new int[3][3];
        int var  = 0 ;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                this.puzzle[i][j] = ++var;
            }
        }
        this.puzzle[2][2] = 0;

    }

    public SlidingPuzzle(int[][] givenPuzzle){
        this.puzzle = givenPuzzle;
        this.finalForm = new int[3][3];
        int var  = 0 ;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                this.finalForm[i][j] = ++var;
            }
        }
        this.finalForm[2][2] = 0;
    }

    //GETTERS
    public int[][] getPuzzle() {
        return this.puzzle;
    }

    public int[][] getFinalForm() {
        return this.finalForm;
    }

    public int getTitle(int r, int c){
        return this.puzzle[r][c];
    }

    //SETTER
    public void setTitle(int r, int c, int value){
        this.puzzle[r][c] = value;
    }

    //GAME FUNCTIONS

    public void movePiece(int r1, int c1, int r2, int c2){
        int aux = this.getTitle(r1, c1);
        this.setTitle(r1, c1, this.getTitle(r2, c2));
        this.setTitle(r2, c2, aux);
    }

    public Boolean isPuzzleSolved(){
        return this.puzzle == this.finalForm;
    }

    public int[] emptyCell(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (this.puzzle[i][j] == 0)
                    return new int[] {i, j};
            }
        }
        return new int[0];
    }

    public ArrayList<SlidingPuzzle> possibleMoves(){
        int[] emptyCellPosition = this.emptyCell();
        int rowEmpty = emptyCellPosition[0];
        int collumnEmpty = emptyCellPosition[1];
        ArrayList<SlidingPuzzle> possibleMoves = new ArrayList<>();
        int [][] provizoriu = this.puzzle;
        if (collumnEmpty - 1 != -1){
            SlidingPuzzle s1 = new SlidingPuzzle();
            s1.movePiece(rowEmpty, collumnEmpty, rowEmpty, collumnEmpty - 1);
            possibleMoves.add(s1);
        }
        if (collumnEmpty + 1 != 3){
            SlidingPuzzle s2 = new SlidingPuzzle(this.puzzle);
            s2.movePiece(rowEmpty, collumnEmpty, rowEmpty, collumnEmpty + 1);
            possibleMoves.add(s2);
        }
        if (rowEmpty - 1 != -1){
            SlidingPuzzle s3 = new SlidingPuzzle(this.puzzle);
            s3.movePiece(rowEmpty, collumnEmpty, rowEmpty - 1, collumnEmpty);
            possibleMoves.add(s3);
        }
        if (rowEmpty + 1 != 3){
            SlidingPuzzle s4 = new SlidingPuzzle(this.puzzle);
            s4.movePiece(rowEmpty, collumnEmpty, rowEmpty + 1, collumnEmpty);
            possibleMoves.add(s4);
        }
        return possibleMoves;
    }


    @Override
    public String toString() {
        StringBuilder puzzleAsString = new StringBuilder("");
        for (int i = 0; i < 3; i++){
            puzzleAsString.append("\n");
            for (int j = 0; j < 3; j++){
                puzzleAsString.append(this.puzzle[i][j]).append(" ");
            }
        }
        return puzzleAsString.toString();
    }

}