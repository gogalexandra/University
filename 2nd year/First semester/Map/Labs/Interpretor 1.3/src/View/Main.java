package View;

import Controller.Controller;
import Model.Expressions.*;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.*;
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
        IRepo repo1 = new Repo(prg1, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.3\\log1");
        Controller ctr1 = new Controller(repo1);

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*',
                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(
                        new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))),
                        new PrintStmt(new VarExp("b"))))));

        PrgState prg2 = new PrgState(ex2);
        IRepo repo2 = new Repo(prg2, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.3\\log2");
        Controller ctr2 = new Controller(repo2);


        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v",
                new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                        VarExp("v"))))));

        PrgState prg3 = new PrgState(ex3);
        IRepo repo3 = new Repo(prg3, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.3\\log3");
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
        IRepo repo4 = new Repo(prg4, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.3\\log4");
        Controller ctr4 = new Controller(repo4);

        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                        new CompStmt(new newAlloc("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

        PrgState prg5 = new PrgState(ex5);
        IRepo repo5 = new Repo(prg5, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.3\\log5");
        Controller ctr5 = new Controller(repo5);


        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new newAlloc("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+', new ValueExp(new IntValue(5)),
                                                        new rH(new rH(new VarExp("a"))))))))));

        PrgState prg6 = new PrgState(ex6);
        IRepo repo6 = new Repo(prg6, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.3\\log6");
        Controller ctr6 = new Controller(repo6);

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))), new CompStmt(
                                new wH("v", new ValueExp(new IntValue(30))),
                                new PrintStmt(new ArithExp('+', new ValueExp(new IntValue(5)),
                                        new rH(new VarExp("v"))))))));

        PrgState prg7 = new PrgState(ex7);
        IRepo repo7 = new Repo(prg7, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.3\\log7");
        Controller ctr7 = new Controller(repo7);

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new newAlloc("a", new VarExp("v")),
                                        new CompStmt(new newAlloc("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new rH(new rH(new VarExp("a")))))))));
        PrgState prg8 = new PrgState(ex8);
        IRepo repo8 = new Repo(prg8, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.3\\log8");
        Controller ctr8 = new Controller(repo8);

        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v",
                                        new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));

        PrgState prg9 = new PrgState(ex9);
        IRepo repo9 = new Repo(prg9, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.3\\log10");
        Controller ctr9 = new Controller(repo9);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
        menu.addCommand(new RunExample("6",ex6.toString(),ctr6));
        menu.addCommand(new RunExample("7",ex7.toString(),ctr7));
        menu.addCommand(new RunExample("8",ex8.toString(),ctr8));
        menu.addCommand(new RunExample("9",ex9.toString(),ctr9));
        menu.show();
    }

}

