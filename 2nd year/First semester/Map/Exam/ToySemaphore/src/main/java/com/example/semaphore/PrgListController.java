package com.example.semaphore;

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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PrgListController implements Initializable {
    private List<IStmt> programStatements;
    private PrgRunController mainWindowController;

    @FXML
    private ListView<String> programListView;

    @FXML
    private Button runButton = new Button();

    public void setMainWindowController(PrgRunController mainWindowController){
        this.mainWindowController = mainWindowController;
    }

    private void buildProgramStatements()
    {
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*',
                        new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(
                        new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))),
                        new PrintStmt(new VarExp("b"))))));

        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v",
                new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new openRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));

        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new newAlloc("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));


        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new newAlloc("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+', new ValueExp(new IntValue(5)),
                                                        new rH(new rH(new VarExp("a"))))))))));

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))), new CompStmt(
                                new wH("v", new ValueExp(new IntValue(30))),
                                new PrintStmt(new ArithExp('+', new ValueExp(new IntValue(5)),
                                        new rH(new VarExp("v"))))))));

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new newAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new newAlloc("a", new VarExp("v")),
                                        new CompStmt(new newAlloc("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new rH(new rH(new VarExp("a")))))))));

        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v",
                                                new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));


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

        IStmt fork1 = new ForkStmt(new CompStmt(new AcquireStmt("cnt"),
                new CompStmt(new wH("v1", new ArithExp('*', new rH(new VarExp("v1")),
                        new ValueExp(new IntValue(10)))), new CompStmt(new PrintStmt(new rH(new VarExp("v1"))),
                        new ReleaseStmt("cnt")))));

        IStmt fork2 = new ForkStmt(new CompStmt(new AcquireStmt("cnt"),
                new CompStmt(new wH("v1", new ArithExp('*', new rH(new VarExp("v1")),
                        new ValueExp(new IntValue(10)))), new CompStmt(
                        new wH("v1", new ArithExp('*', new rH(new VarExp("v1")),
                                new ValueExp(new IntValue(2)))), new CompStmt(
                        new PrintStmt(new rH(new VarExp("v1"))),
                        new ReleaseStmt("cnt"))))));

        IStmt ex11 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("cnt", new IntType()),
                        new CompStmt(new newAlloc("v1", new ValueExp(new IntValue(2))),
                                new CompStmt(new SemaphoreStmt("cnt", new rH(new VarExp("v1")), new ValueExp(new IntValue(1))),
                                        new CompStmt(fork1, new CompStmt(fork2,
                                                new CompStmt(new AcquireStmt("cnt"),
                                                        new CompStmt(new PrintStmt(new ArithExp('-',
                                                                new rH(new VarExp("v1")),
                                                                new ValueExp(new IntValue(1)))),
                                                                new ReleaseStmt("cnt")))))))));


        programStatements = new ArrayList<>(Arrays.asList(ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11));}

    private List<String> getStringRepresentations(){
        return programStatements.stream().map(IStmt::toString).collect(Collectors.toList());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        buildProgramStatements();
        programListView.setItems(FXCollections.observableArrayList(getStringRepresentations()));

        runButton.setOnAction(actionEvent -> {
            int index = programListView.getSelectionModel().getSelectedIndex();

            if(index < 0)
                return;

            PrgState prg = new PrgState(programStatements.get(index));
            List<PrgState> l = new ArrayList<PrgState>();
            l.add(prg);
            MyDictionary<String, Type> typeEnvir = new MyDictionary<>();
            try{
                programStatements.get(index).typecheck(typeEnvir);
            }
            catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Typechecker failed", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            IRepo repo = new Repo(l, "C:\\UBB Sem 3\\Map\\Labs\\demo2\\log" + index);
            Controller ctrl = new Controller(repo);

            mainWindowController.setController(ctrl);
        });
    }

}
