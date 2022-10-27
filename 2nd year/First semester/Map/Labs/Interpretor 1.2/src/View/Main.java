package View;

import Controller.Controller;
import Model.Expressions.ArithExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepo;
import Repository.Repo;
import View.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));

        PrgState prg1 = new PrgState(ex1);
        IRepo repo1 = new Repo(prg1, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.2\\log1");
        Controller ctr1 = new Controller(repo1);

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*',
                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(
                        new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))),
                        new PrintStmt(new VarExp("b"))))));

        PrgState prg2 = new PrgState(ex2);
        IRepo repo2 = new Repo(prg2, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.2\\log2");
        Controller ctr2 = new Controller(repo2);


        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v",
                new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                        VarExp("v"))))));

        PrgState prg3 = new PrgState(ex3);
        IRepo repo3 = new Repo(prg3, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.2\\log3");
        Controller ctr3 = new Controller(repo3);

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new openRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));

        PrgState prg4 = new PrgState(ex4);
        IRepo repo4 = new Repo(prg4, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.2\\logFile");
        Controller ctr4 = new Controller(repo4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.show();
    }

}
//    public static void menu(Controller ctrl){
//        // ex 1:  int v; v = 2; Print(v)
//        IStmt ex1= new CompStmt(new VarDeclStmt("v", new IntType()),
//                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
//                        new PrintStmt(new VarExp("v"))));
//        // ex 2: a=2+3*5;b=a+1;Print(b)
//        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()),
//                new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new ArithExp('*',
//                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),  new CompStmt(
//                        new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))),
//                        new PrintStmt(new VarExp("b"))))));
//
//        // ex 3: bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
//        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v",
//                new IntType()),new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
//                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
//                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
//                        VarExp("v"))))));
//
//        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
//                new CompStmt(new AssignStmt("varf", new ValueExp( new StringValue("test.in"))),
//                        new CompStmt(new openRFile(new VarExp("varf")),
//                                new CompStmt(new VarDeclStmt("varc", new IntType()),
//                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
//                                                new CompStmt(new PrintStmt(new VarExp("varc")),
//                                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
//                                                                new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));
//
//        Repo repo = new Repo();
//
//        PrgState ps1 = new PrgState(ex1);
//        PrgState ps2 = new PrgState(ex2);
//        PrgState ps3 = new PrgState(ex3);
//        PrgState ps4 = new PrgState(ex4);
//
//        repo.addPrg(ps1);
//        repo.addPrg(ps2);
//        repo.addPrg(ps3);
//        repo.addPrg(ps4);
//
//        Controller c = new Controller(repo);
//
//        boolean working = true;
//        while (working) {
//            System.out.println("0. Exit");
//            System.out.println("1. int v; v = 2; Print(v)");
//            System.out.println("2. a = 2+3*5; b = a+1; Print(b)");
//            System.out.println("3. bool a; int v; a = true; (if a then v = 2 else v = 3); Print(v)");
//            System.out.println("4. file");
//            System.out.println("Choose a program: ");
//            Scanner scanner = new Scanner(System.in);
//            try{
//                int cmd = scanner.nextInt();
//                switch (cmd){
//                    case 0:
//                        working = false;
//                        System.out.println("Bye! ");
//                        break;
//                    case 1:
//                        c.allStep(c.getPrgState(0));
//                        break;
//                    case 2:
//                        c.allStep(c.getPrgState(1));
//                        break;
//                    case 3:
//                        c.allStep(c.getPrgState(2));
//                        break;
//                    case 4:
//                        c.allStep(c.getPrgState(3));
//                        break;
//                }
//            }catch (Exception e){
//                System.out.println(e.getMessage());
