/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 John Ashley
 */


package baseline;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//main application. currently putting all classes in here, but they will most likely move around
public class ToDoListApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("application1_gui.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("To Do List");
        stage.setScene(scene);
        stage.show();
    }
    //public void start (Stage stage) throws Exception {
    //
    //Scene scene = new Scene(root);
    //stage.setTitle("name");
    //stage.setScene(scene);
    //stage.show();



    public static void main(String[] args)
    {
        launch(args);
    }



}