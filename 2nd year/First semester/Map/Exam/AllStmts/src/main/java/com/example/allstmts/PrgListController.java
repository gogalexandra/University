package com.example.allstmts;

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
import javafx.scene.control.Button;
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
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))))));

        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewAlloc("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));


        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewAlloc("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new RH(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+', new ValueExp(new IntValue(5)),
                                                        new RH(new RH(new VarExp("a"))))))))));

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new RH(new VarExp("v"))), new CompStmt(
                                new WH("v", new ValueExp(new IntValue(30))),
                                new PrintStmt(new ArithExp('+', new ValueExp(new IntValue(5)),
                                        new RH(new VarExp("v"))))))));

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewAlloc("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewAlloc("a", new VarExp("v")),
                                        new CompStmt(new NewAlloc("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new RH(new RH(new VarExp("a")))))))));

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
                                new CompStmt(new NewAlloc("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new WH("a", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                                        new PrintStmt(new RH(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new RH(new VarExp("a")))))))));

        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt( new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new NewAlloc("a", new ValueExp(new IntValue(20))),
                                new CompStmt(new ForStmt("v", new ValueExp(new IntValue(0)),
                                        new ValueExp(new IntValue(3)), new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))),
                                        new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('*', new VarExp("v"), new RH(new VarExp("a"))))))),
                                        new PrintStmt(new RH(new VarExp("a")))))));


        IStmt ex12 = new CompStmt(new VarDeclStmt("b", new BoolType()),
                new CompStmt( new VarDeclStmt("c", new IntType()),
                        new CompStmt(new AssignStmt("b", new ValueExp(new BoolValue(true))),
                                new CompStmt(
                                        new ConditionalAssigmentStmt("c", new VarExp("b"), new ValueExp(new IntValue(100)),
                                                new ValueExp(new IntValue(200))),
                                        new CompStmt(new PrintStmt(new VarExp("c")),
                                                new CompStmt(
                                                        new ConditionalAssigmentStmt("c", new ValueExp(new BoolValue(false)), new ValueExp(new IntValue(100)),
                                                                new ValueExp(new IntValue(200))),
                                                        new PrintStmt(new VarExp("c"))))))));

        IStmt ex13 = new CompStmt( new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt( new VarDeclStmt("c", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(1))),
                                        new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(2))),
                                                new CompStmt(new AssignStmt("c", new ValueExp(new IntValue(5))),
                                                        new CompStmt(new SwitchStmt(new ArithExp('*', new VarExp("a"), new ValueExp(new IntValue(10))),
                                                                new ArithExp('*', new VarExp("b"), new VarExp("c")),
                                                                new ValueExp(new IntValue(10)),
                                                                new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
                                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new PrintStmt(new ValueExp(new IntValue(200)))),
                                                                new PrintStmt(new ValueExp(new IntValue(100)))),
                                                                new PrintStmt(new ValueExp(new IntValue(300))))))))));

        IStmt ex14 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                        new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("v", new ArithExp('-', new VarExp("v"),new ValueExp(new IntValue(1)))),
                                new CompStmt(new AssignStmt("v", new ArithExp('-', new VarExp("v"),new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("v"))))),
                                new CompStmt(new SleepStmt(10),new PrintStmt(new ArithExp('*', new VarExp("v"),new ValueExp(new IntValue(10))))))));


        IStmt ex15 = new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(0))),
                            new CompStmt(new RepeatStmt(new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                                                                    new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                                                                    new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))),
                                                        new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), "==")),
                                    new CompStmt(new VarDeclStmt("x", new IntType()),
                                        new CompStmt(new VarDeclStmt("y", new IntType()),
                                                new CompStmt(new VarDeclStmt("z", new IntType()),
                                                        new CompStmt(new VarDeclStmt("w", new IntType()),
                                                                new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))),
                                                                        new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(2))),
                                                                                new CompStmt(new AssignStmt("z", new ValueExp(new IntValue(3))),
                                                                                        new CompStmt(new AssignStmt("w", new ValueExp(new IntValue(4))),
                                                                                                    new PrintStmt(new ArithExp('*', new VarExp("v"), new ValueExp(new IntValue(10)))))))))))))));


        programStatements = new ArrayList<>(Arrays.asList(ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12, ex13, ex14, ex15));}

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
//            try{
//                programStatements.get(index).typecheck(typeEnvir);
//            }
//            catch (Exception e){
//                Alert alert = new Alert(Alert.AlertType.ERROR, "Typechecker failed", ButtonType.OK);
//                alert.showAndWait();
//                return;
//            }
            IRepo repo = new Repo(l, "C:\\UBB Sem 3\\Map\\Exam\\AllStmts\\" + index);
            Controller ctrl = new Controller(repo);

            mainWindowController.setController(ctrl);
        });
    }

}
