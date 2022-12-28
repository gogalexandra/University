package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Puzzle {
    private int[][] puzzle;
    private int[][] finalForm;

    //CONSTRUCTORS
    public Puzzle(){
        Integer[] intArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        intList.toArray(intArray);

        int index = 0;
        this.puzzle = new int[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                this.puzzle[i][j] = intArray[index++];
            }
        }
        this.finalForm = new int[3][3];
        index = 0 ;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                this.finalForm[i][j] = ++index;
            }
        }
        this.finalForm[2][2] = 0;

    }

    public Puzzle(int[][] givenPuzzle){
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

    public void setTitle(int r, int c, int value){
        this.puzzle[r][c] = value;
    }

    public int[] getCoordinates(int Title){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (this.puzzle[i][j] == Title)
                    return new int[]{i, j};
            }
        }

        return new int[0];
    }


    // Directions:  up (row + 1, column)
    //              down(row - 1, column)
    //              left(row, column - 1)
    //              right(row, column + 1)

    public boolean movePiece(int r1, int c1, int r2, int c2){
        if ((r2 == -1) || (r2 == 3) || (c2 == -1) || (c2 == 3))
            return false;
        int aux = this.getTitle(r1, c1);
        this.setTitle(r1, c1, this.getTitle(r2, c2));
        this.setTitle(r2, c2, aux);
        return true;
    }


    public boolean move(String direction){
        int emptyCellRow = this.getCoordinates(0)[0];
        int emptyCellColumn = this.getCoordinates(0)[1];
        return switch (direction) {
            case "up" -> this.movePiece(emptyCellRow, emptyCellColumn, emptyCellRow + 1, emptyCellColumn);
            case "down" -> this.movePiece(emptyCellRow, emptyCellColumn, emptyCellRow - 1, emptyCellColumn);
            case "left" -> this.movePiece(emptyCellRow, emptyCellColumn, emptyCellRow, emptyCellColumn + 1);
            case "right" -> this.movePiece(emptyCellRow, emptyCellColumn, emptyCellRow, emptyCellColumn - 1);
            default -> false;
        };
    }

    public Boolean isPuzzleSolved(){
        return this.puzzle == this.finalForm;
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
