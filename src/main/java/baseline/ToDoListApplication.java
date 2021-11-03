package baseline;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//main application. currently putting all classes in here, but they will most likely move around
public class ToDoListApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("assignment05_gui.fxml"));
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

    private String getListTitleInput()
    {
        //ask for input for the title of a new list and return it

        return "s";
    }

    public ToDoList createList(String title)
    {
        ToDoList listOne = new ToDoList();


        //placeholder returns. Will be in all functions
        return listOne;
    }







    public File saveList(ToDoList list)
    {
        //create new file
        File file = new File(list.title);

        //ask for the file location and name for saved data. Probably divide this into multiple function

        //save the lists and all of its info/items to the file



        //return it
        return file;
    }

    public ToDoList loadList(String name)
    {
        //placeholder for now. ignore
        ToDoList list = new ToDoList();

        //takes the string name and searches through all the titles of lists and pulls that out

        //returns the list (possibly change to file?)
        return list;
    }

    private void displayGuide()
    {

    }

}