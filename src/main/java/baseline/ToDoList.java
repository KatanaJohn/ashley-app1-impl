package baseline;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    //a to do list should include a string, item capacity, and list of items.
    String title;
    private final int capacity = 256;
    List<Item> items = new ArrayList<>();


    //setter for class
    public ToDoList()
    {
    }

    //both below are getters
    public int getCapacity() {
        return capacity;
    }

    public String getTitle()
    {
        return title;
    }


    public ToDoList addItemToList(ToDoList listOne, Item item)
    {

        //take object and add it to  todolist

        return listOne;
    }

    public Item searchForItem(ToDoList list, String name)
    {
        //currently a placeholder so we can return. will not be included.
        Item item = new Item();

        //search for the item called by searching through the list that wants to be checked.
        return item;
    }

    public ToDoList removeItemFromList(ToDoList list, Item item)
    {
        //ignore
        ToDoList listOne = new ToDoList();

        //takes the item found from searchForItem and deletes it from the list


        return listOne;
    }

    public ToDoList clearList(ToDoList list)
    {
        return list;
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