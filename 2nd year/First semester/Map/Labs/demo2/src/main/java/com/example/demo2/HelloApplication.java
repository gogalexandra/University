package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;




public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getClassLoader().getResource("runForm2.fxml"));
        Parent mainWindow = mainLoader.load();

        PrgRunController mainWindowController = mainLoader.getController();

        primaryStage.setTitle("Main Window");
        primaryStage.setScene(new Scene(mainWindow, 620, 600));
        primaryStage.show();


        FXMLLoader secondaryLoader = new FXMLLoader();
        secondaryLoader.setLocation(getClass().getClassLoader().getResource("selectForm2.fxml"));
        Parent secondaryWindow = secondaryLoader.load();

        PrgListController selectWindowController = secondaryLoader.getController();
        selectWindowController.setMainWindowController(mainWindowController);

        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Select Window");
        secondaryStage.setScene(new Scene(secondaryWindow, 500, 550));
        secondaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}