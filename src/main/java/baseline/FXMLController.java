//FIXES TO DO ON FUNCTIONS:
    //add item: add boolean column, add to list for file usage later



package baseline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

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
    private TableColumn<Item, String> Completion;
    @FXML
    private Button EditItem;
    @FXML
    private Button DeleteItem;
    @FXML
    private TextField currentDescription;
    @FXML
    private TableView<Item> ListOfItemsTable;
    @FXML
    private Menu Menu;
    @FXML
    private MenuBar MenuBar;
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

    private ToDoList currentList = new ToDoList();


    @FXML
    void initialize() {
        Description.setCellValueFactory(new PropertyValueFactory("Description"));
        //Description.setResizable(false);
        DueDate.setCellValueFactory(new PropertyValueFactory("dueDate"));
        //DueDate.setResizable(false);
        Completion.setCellValueFactory(new PropertyValueFactory("Completion"));
        //statusColumn.setCellValueFactory(new PropertyValueFactory("itemStatus"));
        //statusColumn.setResizable(false);
        ListOfItemsTable.setEditable(true);
        //Completion.setCellValueFactory(new PropertyValueFactory<Item,Boolean>("check"));
        Completion.setCellFactory(column -> new CheckBoxTableCell());

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
            // get/set local date
            LocalDate myDate = dueDate.getValue();
            String currentDate = myDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
            item.setDueDate(currentDate.toString());


            //insert items into table
            ListOfItemsTable.getItems().add(item);

            currentDescription.clear();
            System.out.println(currentDate);
            System.out.println("new item");

            currentList.items.add(item);
        }

        //SET BOOLEAN STATUS



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
        ObservableList<Item> itemSelected, allItems;
        allItems = ListOfItemsTable.getItems();
        itemSelected = ListOfItemsTable.getSelectionModel().getSelectedItems();

        itemSelected.forEach(allItems::remove);
    }


    public void editItem(ActionEvent e)
    {
        int index = ListOfItemsTable.getSelectionModel().getSelectedIndex();

        Item item = new Item();
        item.setDescription(currentDescription.getText());
        // get/set local date
        LocalDate myDate = dueDate.getValue();
        String currentDate = myDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        item.setDueDate(currentDate.toString());

        ListOfItemsTable.getItems().set(index, item);
    }






    //----------------------------------------------------------------------------------------
/*
    private void updateTable()
    {
        List<Item> incompleteItemList = new ArrayList<>();
        List<Item> completeItemList = new ArrayList<>();

        for(int i = 0; i < currentList.size(); i++)
        {
            Item currentItem = currentList.get(i);

            if(currentItem.getCompletionStatus())
            {
                completeItemList.add(currentItem);
            }
            else
            {
                incompleteItemList.add(currentItem);
            }
        }
    }*/



}
