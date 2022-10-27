package View;

import Controller.Controller;
import Model.Expressions.ArithExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Repository.Repo;

import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Repo myRepo = new Repo();
        Controller myController = new Controller(myRepo);
        menu(myController);
    }
    public static void menu(Controller ctrl){
        // ex 1:  int v; v = 2; Print(v)
        IStmt ex1= new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        // ex 2: a=2+3*5;b=a+1;Print(b)
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new ArithExp('*',
                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),  new CompStmt(
                        new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))),
                        new PrintStmt(new VarExp("b"))))));

        // ex 3: bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v",
                new IntType()),new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                        VarExp("v"))))));

        Repo repo = new Repo();

        PrgState ps1 = new PrgState(ex1);
        PrgState ps2 = new PrgState(ex2);
        PrgState ps3 = new PrgState(ex3);

        repo.addPrg(ps1);
        repo.addPrg(ps2);
        repo.addPrg(ps3);

        Controller c = new Controller(repo);

        boolean working = true;
        while (working) {
            System.out.println("0. Exit");
            System.out.println("1. int v; v = 2; Print(v)");
            System.out.println("2. a = 2+3*5; b = a+1; Print(b)");
            System.out.println("3. bool a; int v; a = true; (if a then v = 2 else v = 3); Print(v)");
            System.out.println("Choose a program: ");
            Scanner scanner = new Scanner(System.in);
            try{
                int cmd = scanner.nextInt();
                switch (cmd){
                    case 0:
                        working = false;
                        System.out.println("Bye! ");
                        break;
                    case 1:
//                        System.out.println("1. One step execution");
//                        System.out.println("2. Complete execution");
//                        System.out.println("Choose method to display: ");
//                        int op = scanner.nextInt();
//                        if (op == 1)
//                            c.oneStep(c.getPrgState(0));
//                        else
//                            c.allStep(c.getPrgState(0));
//                        break;
                        c.allStep(c.getPrgState(0));
                        break;
                    case 2:
//                        System.out.println("One step execution");
//                        System.out.println("Complete execution");
//                        String op1 = scanner.toString();
//                        if (op1.equals("one step"))
//                            c.oneStep(c.getPrgState(1));
//                        else
//                            c.allStep(c.getPrgState(1));
//                        break;
                        c.allStep(c.getPrgState(1));
                        break;
                    case 3:
//                        System.out.println("One step execution");
//                        System.out.println("Complete execution");
//                        String op2 = scanner.toString();
//                        if (op2.equals("one step"))
//                            c.oneStep(c.getPrgState(2));
//                        else
//                            c.allStep(c.getPrgState(2));
//                        break;
                        c.allStep(c.getPrgState(2));
                        break;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }
}
