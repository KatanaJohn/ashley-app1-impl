/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 John Ashley
 */


package baseline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.scene.control.MenuItem;

public class FXMLController {


    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button addItem;
    @FXML
    private TableColumn<Item, String> description;
    @FXML
    private TableColumn<Item, String> dueDate;
    @FXML
    private TableColumn<Item, Boolean> completion;
    @FXML
    private Button editItem;
    @FXML
    private Button deleteItem;
    @FXML
    private Button deleteAll;
    @FXML
    private TextField currentDescription;
    @FXML
    private TableView<Item> tableOfItems;
    @FXML
    private Menu menu;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuCompleteButton;
    @FXML
    private MenuItem menuIncompleteButton;
    @FXML
    private MenuItem viewAllButton;
    @FXML
    private MenuItem openButton;
    @FXML
    private MenuItem saveButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Menu viewListButton;
    @FXML
    private CheckBox completionCheckBox;

    private int index = 0;
    private final RequirementChecker checker = new RequirementChecker();


    @FXML
    void initialize() {
        //set table cell for description
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));

        //set table cell for  due dates
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        //set table cell for complete/incompletes
        completion.setCellValueFactory(new PropertyValueFactory<>("completionStatus"));
        completion.setOnEditCommit(event -> {
            Item item = event.getRowValue();
            item.setCompletionStatus(event.getNewValue());
            tableOfItems.refresh();
        });

    }




    //add button clicked, add info
    @FXML
    void addItem(ActionEvent e)
    {
        //when add item is clicked, create a new item
        Item item = new Item();
        //get description from textbox
        String newDescription = currentDescription.getText();

        //check for its length
        if(checker.checkDescription(newDescription))
        {
            //set description by getting it
            item.setDescription(currentDescription.getText());
            // get/set local date to N/A if null, else set the date to what the datePicker has
            String currentDate = checker.checkDueDate(datePicker.getValue());
            item.setDueDate(currentDate);


            //set completion status based on if selected or not
            item.setCompletionStatus(completionCheckBox.isSelected());
            System.out.println(completionCheckBox.isSelected());


            //insert items into table and to do list
            tableOfItems.getItems().add(item);
            Item.toDoList.add(item);


            //clear description box and set dueDate box back to null
            currentDescription.clear();
            datePicker.setValue(null);
        }

        //create item boxes in table

    }

    @FXML
    void deleteItem(ActionEvent e)
    {
        //get selected row and get all the items within the list
        Item selectedItem = tableOfItems.getSelectionModel().getSelectedItem();
        ObservableList<Item> itemSelected;
        ObservableList<Item> allItems;
        allItems = tableOfItems.getItems();
        itemSelected = tableOfItems.getSelectionModel().getSelectedItems();

        //remove the item from the display and todolist
        Item.toDoList.remove(selectedItem);
        itemSelected.forEach(allItems::remove);
    }

    @FXML
    void deleteAll (ActionEvent event){
        //clears the entire table and list
        Item.getToDoList().clear();
        tableOfItems.getItems().clear();
    }


    //functions very similarly to addItem with the addition of changing the specific index
    @FXML
    void editItem(ActionEvent e)
    {
        //get index of selected row
        int idx = tableOfItems.getSelectionModel().getSelectedIndex();
        //get current description from user text box entry
        String newDescription = currentDescription.getText();

        //check for length
        if(checker.checkDescription(newDescription)) {
            //create a new item and set the description
            Item item = new Item();
            item.setDescription(currentDescription.getText());

            // get/set local date. If null, set to N/A. else adds the date from datePicker.
            String currentDate = checker.checkDueDate(datePicker.getValue());
            item.setDueDate(currentDate);

            //sets completion status
            item.setCompletionStatus(completionCheckBox.isSelected());

            //replaces the selected index with the new item in the table and list
            tableOfItems.getItems().set(idx, item);
            Item.toDoList.set(idx, item);

            //resets current description and due date
            datePicker.setValue(null);
            currentDescription.clear();
        }
    }

    @FXML
    void loadFile(ActionEvent e) throws FileNotFoundException
    {
        //create file chooser and set the title/filters
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import List File (.txt");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        //set a file to the selected file on the screen (basically like any typical file load in an app.)
        File file = fileChooser.showOpenDialog(tableOfItems.getScene().getWindow());

        //import the list so we can divide the item information into the correct columns
        importList(file);

        //displays each item in the list
        for(int i = 0; i < Item.getToDoList().size(); i++)
        {
            displayList();
        }

    }

    @FXML
    void importList(File selectedFile) throws FileNotFoundException
    {
        //creates filereader to read the passed in file
        FileReader reader = new FileReader(selectedFile);
        //clear old to do list
        Item.getToDoList().clear();
        if(this.tableOfItems!= null){
            tableOfItems.getItems().clear();
        }
        //set the index back to 0 for when we add
        index = 0;

        //try/catch for reading in the list
        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            boolean bool;
            //reads through the list until theres nothing left
            while ((line = br.readLine()) != null) {
                //splice each line whenever there is a comma
                String[] tableInfo = line.split(",");

                //checks for a valid file. If not valid, breaks/does not load file
                if(tableInfo.length != 3){
                    System.out.println("length fail\n");
                    break;
                }
                //if the file is valid, adds the information
                else{
                    bool = !tableInfo[2].equals("false");
                    Item.getToDoList().add(new Item(tableInfo[0], tableInfo[1], bool));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayList()
    {
        //displays all the information in the list on the table and increases the index by 1 for adding.
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        completion.setCellValueFactory(new PropertyValueFactory<>("completionStatus"));
        tableOfItems.getItems().add(Item.getToDoList().get(index));
        index++;
    }

    @FXML
    void saveList(ActionEvent e)
    {
        //creates filechooser and asks for file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a .txt file to save");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        //creates a file based on the title set/file chosen
        File file = fileChooser.showSaveDialog(tableOfItems.getScene().getWindow());

        //gets the selected file
        getToDoList(file);
    }

    public void getToDoList(File selectedFile) {
        //if the file can be saved in a valid way, it will be added/saved.
        try (PrintWriter writer = new PrintWriter(selectedFile)) {
            for (Item i : Item.getToDoList()) {
                //writes the information into the file and separates them by commas so we have a way to load it later.
                writer.println(i.getDescription() + "," + i.getDueDate() + "," + i.getCompletionStatus());
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("Invalid File");
        }
    }



    @FXML
    public void displayCompletedItems(ActionEvent e)
    {
        //make and observable list for completed items to display
        ObservableList<Item> completedItems = FXCollections.observableArrayList();

        //clear the table
        tableOfItems.getItems().clear();

        //call a method to create a list of items with the "true" value
        createCompletedList(completedItems);

        //refresh the list
        tableOfItems.refresh();
        tableOfItems.setItems(completedItems);
    }

    private ObservableList<Item> createCompletedList(ObservableList<Item> items)
    {
        //clear any items in the list
        items.clear();

        //for each item, check if the completion status is complete. If not, change it.
        for(Item i : Item.getToDoList())
        {
            if(i.getCompletionStatus())
            {
                items.add(i);
            }
        }
        return items;
    }

    //essentially the same exact thing as completed
    @FXML
    void displayIncompletedItems(ActionEvent e)
    {
        //make an observable list for completed items to display
        ObservableList<Item> completedItems = FXCollections.observableArrayList();

        //clear the table
        tableOfItems.getItems().clear();

        //call a method to create a list of items with the "true" value
        createIncompletedList(completedItems);

        //refresh the list
        tableOfItems.refresh();
        tableOfItems.setItems(completedItems);
    }

    private void createIncompletedList(ObservableList<Item> items)
    {
        //clear any items in the list
        items.clear();

        //for each item, check if the completion status is complete. If not, change it.
        for(Item i : Item.getToDoList())
        {
            if(!i.getCompletionStatus())
            {
                items.add(i);
            }
        }
    }

    //almost the same as the above, but simpler
    @FXML
    void displayAllItems(ActionEvent e)
    {
        ObservableList<Item> allItems = FXCollections.observableArrayList();

        tableOfItems.getItems().clear();

        //just display all the items in the list
        allItems.addAll(Item.getToDoList());

        //refresh list
        tableOfItems.refresh();
        tableOfItems.setItems(allItems);
    }





}
