/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 John Ashley
 */

//THINGS TO DO:
    //Lots of warnings
    //Guide
    //plantuml diagram
    //tests
    //video



package baseline;
import com.google.gson.JsonParser;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;
import javafx.scene.control.MenuItem;

public class FXMLController {


    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button AddItem;
    @FXML
    private MenuItem CloseList;
    @FXML
    private TableColumn<Item, String> Description;
    @FXML
    private TableColumn<Item, String> DueDate;
    @FXML
    private TableColumn<Item, Boolean> Completion;
    @FXML
    private Button EditItem;
    @FXML
    private Button DeleteItem;
    @FXML
    private Button DeleteAll;
    @FXML
    private TextField currentDescription;
    @FXML
    private TableView<Item> ListOfItemsTable;
    @FXML
    private Menu Menu;
    @FXML
    private MenuBar MenuBar;
    @FXML
    private MenuItem menuComplete;
    @FXML
    private MenuItem menuIncomplete;
    @FXML
    private MenuItem ViewAll;
    @FXML
    private MenuItem OpenList;
    @FXML
    private MenuItem SaveList;
    @FXML
    private DatePicker dueDate;
    @FXML
    private CheckMenuItem ViewCompleted;
    @FXML
    private CheckMenuItem ViewIncompleted;
    @FXML
    private Menu ViewList;
    @FXML
    private CheckBox completionCheckBox;

    private Item currentList = new Item();
    private int index = 0;
    Item currentItem;


    @FXML
    void initialize() {
        //set table cell for description
        Description.setCellValueFactory(new PropertyValueFactory("Description"));

        //set table cell for  due dates
        DueDate.setCellValueFactory(new PropertyValueFactory("dueDate"));

        //set table cell for complete/incompletes
        //Completion.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
        Completion.setCellValueFactory(new PropertyValueFactory<Item, Boolean>("completionStatus"));
        Completion.setOnEditCommit(event -> {
            Item item = event.getRowValue();
            item.setCompletionStatus(event.getNewValue());
            ListOfItemsTable.refresh();
        });
        //ListOfItemsTable.setEditable(true);

    }




    //add button clicked, add info
    public void addItem(ActionEvent e)
    {
        //when add item is clicked, create a new item
        Item item = new Item();
        //get description from textbox
        String newDescription = currentDescription.getText();

        //check for its length
        if(newDescription.length() > 1 && newDescription.length() < 256)
        {
            //set description by getting it
            item.setDescription(currentDescription.getText());
            // get/set local date to N/A if null, else set the date correctly
            if(dueDate.getValue() == null)
            {
                item.setDueDate("N/A");
            }
            else
            {
                LocalDate myDate = dueDate.getValue();
                String currentDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                item.setDueDate(currentDate.toString());
            }

            //set completion status based on if selected or not
            item.setCompletionStatus(completionCheckBox.isSelected());
            System.out.println(completionCheckBox.isSelected());


            //insert items into table and to do list
            ListOfItemsTable.getItems().add(item);
            currentList.toDoList.add(item);


            //clear description box and set dueDate box back to null
            currentDescription.clear();
            dueDate.setValue(null);
        }

        //create item boxes in table

    }

    public void getDate(ActionEvent e)
    {
        LocalDate myDate = dueDate.getValue();
        String currentDate = dueDate.toString();
        System.out.println(currentDate);
    }



    public void deleteItem(ActionEvent e)
    {
        //get selected row and get all the items within the list
        Item selectedItem = ListOfItemsTable.getSelectionModel().getSelectedItem();
        ObservableList<Item> itemSelected, allItems;
        allItems = ListOfItemsTable.getItems();
        itemSelected = ListOfItemsTable.getSelectionModel().getSelectedItems();

        //remove the item from the display and todolist
        currentList.toDoList.remove(selectedItem);
        itemSelected.forEach(allItems::remove);
    }

    public void deleteAll (ActionEvent event){
        //clears the entire table and list
        Item.getToDoList().clear();
        ListOfItemsTable.getItems().clear();
    }


    //functions very similarly to addItem with the addition of changing the specific index
    public void editItem(ActionEvent e)
    {
        //get index of selected row
        int index = ListOfItemsTable.getSelectionModel().getSelectedIndex();
        //get current description from user text box entry
        String newDescription = currentDescription.getText();

        //check for length
        if(newDescription.length() > 1 && newDescription.length() < 256) {
            //create a new item and set the description
            Item item = new Item();
            item.setDescription(currentDescription.getText());
            // get/set local date. If null, set to N/A. else adds the date from datePicker.
            if(dueDate.getValue() == null)
            {
                item.setDueDate("N/A");
            }
            else
            {
                LocalDate myDate = dueDate.getValue();
                String currentDate = myDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
                item.setDueDate(currentDate.toString());
            }
            //sets completion status
            item.setCompletionStatus(completionCheckBox.isSelected());

            //replaces the selected index with the new item in the table and list
            ListOfItemsTable.getItems().set(index, item);
            currentList.toDoList.set(index, item);

            //resets current description and due date
            dueDate.setValue(null);
            currentDescription.clear();
        }
    }

    public void loadFile(ActionEvent e) throws FileNotFoundException
    {
        //create file chooser and set the title/filters
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import List File (.txt");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        //set a file to the selected file on the screen (basically like any typical file load in an app.)
        File file = fileChooser.showOpenDialog(ListOfItemsTable.getScene().getWindow());

        //import the list so we can divide the item information into the correct columns
        importList(file);

        //displays each item in the list
        for(int i = 0; i < Item.getToDoList().size(); i++)
        {
            displayList();
        }

    }

    public void importList(File selectedFile) throws FileNotFoundException
    {
        //creates filereader to read the passed in file
        FileReader reader = new FileReader(selectedFile);
        //clear old to do list
        Item.getToDoList().clear();
        if(this.ListOfItemsTable!= null){
            ListOfItemsTable.getItems().clear();
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
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        DueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        Completion.setCellValueFactory(new PropertyValueFactory<>("completionStatus"));
        ListOfItemsTable.getItems().add(Item.getToDoList().get(index));
        index++;
    }

    public void saveList(ActionEvent e)
    {
        //creates filechooser and asks for file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a .txt file to save");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        //creates a file based on the title set/file chosen
        File file = fileChooser.showSaveDialog(ListOfItemsTable.getScene().getWindow());

        //gets the selected file
        getToDoList(file);
    }

    void getToDoList(File selectedFile) {
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




    public void displayCompletedItems(ActionEvent e)
    {
        //make and observable list for completed items to display
        ObservableList<Item> completedItems = FXCollections.observableArrayList();

        //clear the table
        ListOfItemsTable.getItems().clear();

        //call a method to create a list of items with the "true" value
        createCompletedList(true, completedItems);

        //refresh the list
        ListOfItemsTable.refresh();
        ListOfItemsTable.setItems(completedItems);
    }

    public ObservableList<Item> createCompletedList(boolean completed, ObservableList<Item> items)
    {
        //clear any items in the list
        items.clear();

        //for each item, check if the completion status is complete. If not, change it.
        for(Item i : Item.getToDoList())
        {
            if(completed == i.getCompletionStatus())
            {
                items.add(i);
            }
        }
        return items;
    }

    //essentially the same exact thing as completed
    public void displayIncompletedItems(ActionEvent e)
    {
        //make an observable list for completed items to display
        ObservableList<Item> completedItems = FXCollections.observableArrayList();

        //clear the table
        ListOfItemsTable.getItems().clear();

        //call a method to create a list of items with the "true" value
        createIncompletedList(false, completedItems);

        //refresh the list
        ListOfItemsTable.refresh();
        ListOfItemsTable.setItems(completedItems);
    }

    public ObservableList<Item> createIncompletedList(boolean completed, ObservableList<Item> items)
    {
        //clear any items in the list
        items.clear();

        //for each item, check if the completion status is complete. If not, change it.
        for(Item i : Item.getToDoList())
        {
            if(completed == i.getCompletionStatus())
            {
                items.add(i);
            }
        }
        return items;
    }

    //almost the same as the above, but simpler
    public void displayAllItems(ActionEvent e)
    {
        ObservableList<Item> allItems = FXCollections.observableArrayList();

        ListOfItemsTable.getItems().clear();

        //just display all the items in the list
        for(Item i : Item.getToDoList())
        {
            allItems.add(i);
        }

        //refresh list
        ListOfItemsTable.refresh();
        ListOfItemsTable.setItems(allItems);
    }





}
