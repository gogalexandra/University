package com.example.demo2;

import Controller.Controller;
import Model.Expressions.ValueExp;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Statements.VarDeclStmt;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIStack;
import Model.Utils.MyList;
import Model.Values.Value;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class PrgRunController implements Initializable {
    private Controller controller;

    @FXML
    private TableView<Map.Entry<Integer, Value>> heapTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> heapAddressColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, String> heapValueColumn;

    @FXML
    private TableView<Map.Entry<Value, BufferedReader>> fileTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, String> fileIdentifierColumn;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, String> fileNameColumn;

    @FXML
    private TableView<Map.Entry<String, Value>> symbolTableView;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> symbolTableVariableColumn;

    @FXML
    private TableColumn<Map.Entry<String, String>, String> symbolTableValueColumn;

    @FXML
    private ListView<Value> outputListView;

    @FXML
    private ListView<Value> filesListView;

    @FXML
    private ListView<Integer> programStateListView;

    @FXML
    private ListView<String> executionStackListView;

    @FXML
    private TextField numberOfProgramStatesTextField;

    @FXML
    private Button executeOneStepButton;

    public void setController(Controller controller){
        this.controller = controller;
        populateProgramStateIdentifiers();
    }

    private List<Integer> getProgramStateIds(List<PrgState> programStateList) {
        return programStateList.stream().map(PrgState::getId).collect(Collectors.toList());
    }

    private void populateProgramStateIdentifiers() {
        List<PrgState> programStates = controller.getRepo().getPrgList();
        programStateListView.setItems(FXCollections.observableList(getProgramStateIds(programStates)));

        numberOfProgramStatesTextField.setText("" + programStates.size());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        heapAddressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapValueColumn.setCellValueFactory(p ->  new SimpleStringProperty(p.getValue().toString()));

//        fileIdentifierColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().toString()));
//        fileNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().toString()));

        symbolTableVariableColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey() + ""));
        Object value;
        symbolTableValueColumn.setCellValueFactory(p -> new SimpleStringProperty( p.getValue().toString()));


        programStateListView.setOnMouseClicked(mouseEvent -> { changeProgramState(getCurrentProgramState()); });

        executeOneStepButton.setOnAction(actionEvent -> {
            try {
                executeOneStep();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void executeOneStep() throws Exception {
        if(controller == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        boolean programStateLeft = getCurrentProgramState().getStk().isEmpty();
        if(programStateLeft){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        controller.executeOneStep();

        changeProgramState(getCurrentProgramState());
        populateProgramStateIdentifiers();


    }

    private void changeProgramState(PrgState currentProgramState) {
        if(currentProgramState == null)
            return;
        populateExecutionStack(currentProgramState);
        populateSymbolTable(currentProgramState);
        populateOutput(currentProgramState);
        populateFileTable(currentProgramState);
        populateHeapTable(currentProgramState);
    }

    private void populateHeapTable(PrgState currentProgramState) {

        Map<Integer, Value> heapTable =  currentProgramState.getHeap().getContent();

        List<Map.Entry<Integer, Value>> heapTableList = new ArrayList<>();
        for(Map.Entry<Integer, Value> entry : heapTable.entrySet())
            heapTableList.add(entry);

        heapTableView.setItems(FXCollections.observableList(heapTableList));
        heapTableView.refresh();
    }

    private void populateFileTable(PrgState currentProgramState) {
//        MyIDictionary<Value, BufferedReader> fileTable = currentProgramState.getFileTable();
//        Map<Value, BufferedReader> fileTableMap = new HashMap<>();
//        for (Map.Entry<Value, BufferedReader> entry : fileTable.getAll().entrySet())
//            fileTableMap.put(entry.getKey(), entry.getValue());
//
//        List<Map.Entry<Value, BufferedReader>> fileTableList = new ArrayList<>(fileTableMap.entrySet());
//        fileTableView.setItems(FXCollections.observableList(fileTableList));
//        fileTableView.refresh();
        List<Value> output = new ArrayList<>(currentProgramState.getFileTable().getAll().keySet());
        filesListView.setItems(FXCollections.observableList(output));
        filesListView.refresh();
    }

    private void populateOutput(PrgState currentProgramState) {
        List<Value> output = new ArrayList<>(currentProgramState.getOutput().getAll());
        outputListView.setItems(FXCollections.observableList(output));
        outputListView.refresh();
    }

    private void populateSymbolTable(PrgState currentProgramState) {
        MyIDictionary<String, Value> symbolTable = currentProgramState.getSymTable();

        List<Map.Entry<String, Value>> symbolTableList = new ArrayList<>();
        for(Map.Entry<String, Value> entry : symbolTable.getAll().entrySet())
            symbolTableList.add(entry);
        symbolTableView.setItems(FXCollections.observableList(symbolTableList));
        symbolTableView.refresh();
    }

    private void populateExecutionStack(PrgState currentProgramState) {
        MyIStack<IStmt> executionStack = currentProgramState.getStk();

        List<String> executionStackList = new ArrayList<>();
        for(IStmt s : executionStack.getStack()){
            executionStackList.add(s.toString());
        }

        executionStackListView.setItems(FXCollections.observableList(executionStackList));
        executionStackListView.refresh();
    }

    private PrgState getCurrentProgramState(){
        if(programStateListView.getSelectionModel().getSelectedIndex() == -1)
            return null;

        int currentId = programStateListView.getSelectionModel().getSelectedItem();
        return controller.getRepo().getProgramStateWithId(currentId);
    }
}