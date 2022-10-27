package View;

import Controller.Controller;
import Model.Expressions.*;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.*;
import Model.Utils.MyDictionary;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Repository.IRepo;
import Repository.Repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));

        MyDictionary<String, Type> typeEnvir1 = new MyDictionary<>();
        ex1.typecheck(typeEnvir1);
        PrgState prg1 = new PrgState(ex1);
        List<PrgState> l1 = new ArrayList<PrgState>(){{add(prg1); }};
        IRepo repo1 = new Repo(l1, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.4\\log1");
        Controller ctr1 = new Controller(repo1);

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*',
                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(
                        new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))),
                        new PrintStmt(new VarExp("b"))))));


        MyDictionary<String, Type> typeEnvir2 = new MyDictionary<>();
        ex2.typecheck(typeEnvir2);
        PrgState prg2 = new PrgState(ex2);
        List<PrgState> l2 = new ArrayList<PrgState>(){{add(prg2); }};
        IRepo repo2 = new Repo(l2, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.4\\log2");
        Controller ctr2 = new Controller(repo2);


        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v",
                new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));


        MyDictionary<String, Type> typeEnvir3 = new MyDictionary<>();
        ex3.typecheck(typeEnvir3);
        PrgState prg3 = new PrgState(ex3);
        List<PrgState> l3 = new ArrayList<PrgState>(){{add(prg3); }};
        IRepo repo3 = new Repo(l3, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.4\\log3");
        Controller ctr3 = new Controller(repo3);

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new openRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));


        MyDictionary<String, Type> typeEnvir4 = new MyDictionary<>();
        ex4.typecheck(typeEnvir4);
        PrgState prg4 = new PrgState(ex4);
        List<PrgState> l4 = new ArrayList<PrgState>(){{add(prg4); }};
        IRepo repo4 = new Repo(l4, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.4\\log4");
        Controller ctr4 = new Controller(repo4);

        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                        new CompStmt(new newAlloc("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

        MyDictionary<String, Type> typeEnvir5 = new MyDictionary<>();
        ex5.typecheck(typeEnvir5);
        PrgState prg5 = new PrgState(ex5);
        List<PrgState> l5 = new ArrayList<PrgState>(){{add(prg5); }};
        IRepo repo5 = new Repo(l5, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.4\\log5");
        Controller ctr5 = new Controller(repo5);


        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new newAlloc("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+', new ValueExp(new IntValue(5)),
                                                        new rH(new rH(new VarExp("a"))))))))));

        MyDictionary<String, Type> typeEnvir6 = new MyDictionary<>();
        ex6.typecheck(typeEnvir6);
        PrgState prg6 = new PrgState(ex6);
        List<PrgState> l6 = new ArrayList<PrgState>(){{add(prg6); }};
        IRepo repo6 = new Repo(l6, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.4\\log6");
        Controller ctr6 = new Controller(repo6);

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))), new CompStmt(
                                new wH("v", new ValueExp(new IntValue(30))),
                                new PrintStmt(new ArithExp('+', new ValueExp(new IntValue(5)),
                                        new rH(new VarExp("v"))))))));

        MyDictionary<String, Type> typeEnvir7 = new MyDictionary<>();
        ex7.typecheck(typeEnvir7);
        PrgState prg7 = new PrgState(ex7);
        List<PrgState> l7 = new ArrayList<PrgState>(){{add(prg7); }};
        IRepo repo7 = new Repo(l7, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.4\\log7");
        Controller ctr7 = new Controller(repo7);

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new newAlloc("a", new VarExp("v")),
                                        new CompStmt(new newAlloc("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new rH(new rH(new VarExp("a")))))))));

        MyDictionary<String, Type> typeEnvir8 = new MyDictionary<>();
        ex8.typecheck(typeEnvir8);
        PrgState prg8 = new PrgState(ex8);
        List<PrgState> l8 = new ArrayList<PrgState>(){{add(prg8); }};
        IRepo repo8 = new Repo(l8, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.4\\log8");
        Controller ctr8 = new Controller(repo8);

        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v",
                                        new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));

        MyDictionary<String, Type> typeEnvir9 = new MyDictionary<>();
        ex9.typecheck(typeEnvir9);
        PrgState prg9 = new PrgState(ex9);
        List<PrgState> l9 = new ArrayList<PrgState>(){{add(prg9); }};
        IRepo repo9 = new Repo(l9, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.4\\log9");
        Controller ctr9 = new Controller(repo9);

        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt( new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new newAlloc("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new wH("a", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                                        new PrintStmt(new rH(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new rH(new VarExp("a")))))))));

        MyDictionary<String, Type> typeEnvir10 = new MyDictionary<>();
        ex10.typecheck(typeEnvir10);
        PrgState prg10 = new PrgState(ex10);
        List<PrgState> l10 = new ArrayList<PrgState>();
        l10.add(prg10);
        IRepo repo10 = new Repo(l10, "C:\\UBB Sem 3\\Map\\Labs\\Interpretor 1.4\\log10");
        Controller ctr10 = new Controller(repo10);

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
        menu.addCommand(new RunExample("10",ex10.toString(),ctr10));
        menu.show();
    }

}

