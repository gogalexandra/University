package Main;

import Model.Puzzle;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Puzzle s = new Puzzle();
        System.out.println("The puzzle you need to solve: \n" + s.toString());
        Scanner scanner = new Scanner(System.in);
        while( !s.isPuzzleSolved()){
            System.out.printf("Give direction(up/ down/ left/ right): ");
            String direction = scanner.nextLine();
            if (!s.move(direction))
                System.out.println("Wrong move!!!");
            System.out.println(s.toString());
        }
        System.out.println("Puzzle solved!");
    }
}
