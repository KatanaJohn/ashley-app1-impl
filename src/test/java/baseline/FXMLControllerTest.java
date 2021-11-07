package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FXMLControllerTest {

    @Test
    //creates a list of 2 items and should only get ones labeled true
    void completedListTest()
    {
        Item.getToDoList().clear();
        ObservableList<Item> items = FXCollections.observableArrayList();;
        Item item1 = new Item("TestFalse", "2021-11-03", false);
        Item item2 = new Item("TestTrue", "2021-11-02", true);
        Item.toDoList.add(0, item1);
        Item.toDoList.add(1, item2);

        for(Item i : Item.getToDoList())
        {
            if(i.getCompletionStatus())
            {
                items.add(i);
            }
        }

        assertEquals("TestTrue", items.get(0).description);

    }

    @Test
    void incompletedListTest()
    {
        Item.getToDoList().clear();
        ObservableList<Item> items = FXCollections.observableArrayList();;
        Item item1 = new Item("TestFalse", "2021-11-03", false);
        Item item2 = new Item("TestTrue", "2021-11-02", true);
        Item.toDoList.add(0, item1);
        Item.toDoList.add(1, item2);

        for(Item i : Item.getToDoList())
        {
            if(!i.getCompletionStatus())
            {
                items.add(i);
            }
        }

        assertEquals("TestFalse", items.get(0).description);

    }

}