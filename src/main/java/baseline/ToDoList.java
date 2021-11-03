package baseline;

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
        Item item = new Item("TEST1", "test2");

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
}