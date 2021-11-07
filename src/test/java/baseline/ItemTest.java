package baseline;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {


    @Test
    void createItem()
    {
        Item item = new Item("Test", "2021-11-02",  true);


        assertTrue((Objects.equals(item.description, "Test") &&
                Objects.equals(item.dueDate, "2021-11-02")
                && item.completion));


    }

    @Test
    void getDescription()
    {
        Item item = new Item("Test", "2021-11-02",  true);

        assertEquals(item.getDescription(), "Test");
    }

    @Test
    void setDescription()
    {
        Item item = new Item("Test", "2021-11-02",  true);
        item.setDescription("New!");
        assertEquals("New!", item.getDescription());
    }

    @Test
    void getDueDate()
    {
        Item item = new Item("Test", "2021-11-02",  true);

        assertEquals(item.getDueDate(), "2021-11-02");
    }

    @Test
    void setDueDate()
    {
        Item item = new Item("Test", "2021-11-02",  true);
        item.setDueDate("2021-11-02");
        assertEquals("2021-11-02", item.getDueDate());
    }

    @Test
    void getCompletionStatus()
    {
        Item item = new Item("Test", "2021-11-02",  true);

        assertEquals(item.getCompletionStatus(), true);
    }

    @Test
    void setCompletionStatus()
    {
        Item item = new Item("Test", "2021-11-02",  true);
        item.setCompletionStatus(false);
        assertEquals(false, item.getCompletionStatus());
    }

    @Test
    void getToDoList()
    {
        Item.getToDoList().clear();
        Item item = new Item("Test", "2021-11-02",  true);
        item.toDoList.add(item);
        item = new Item("Test2", "2021-11-03",  false);

        int i = 0;
        for(Item itm : Item.getToDoList())
        {
            assertEquals(item.toDoList.get(i).toString(), itm.toString());
        }

    }



}