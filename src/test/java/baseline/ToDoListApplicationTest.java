package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void testCreateList()
    {
        //create a to do list including a title and 3 items

        //call create list

        //assert equal the list / title or items
    }

    @Test
    void testAddItemToList()
    {
        //create a list with one item and create an expected item

        //add the item to the list through the function

        //assert that the second item is the newly added item
    }

    @Test
    void testSearchForItem()
    {
        //create a list with two items

        //pass a string that matches the name of one of the items into the function to find it

        //assert the item exists
    }

    @Test
    void testRemoveItem()
    {
        //create a list with 2 items

        //may have to search for item before following step

        //pass a list and a specific item into the tested function to remove it

        //assert that the item no longer exists within the list
    }

    @Test
    void testEditItemDescription()
    {
        //create an item with a random description

        //create an expected string description

        //call the function with the expected string and the current item

        //assert the description within the item is equal to the expected string
    }

    @Test
    void editDueDate()
    {
        //create an item with a random due date

        //create an expected string

        //call the function with the expected string and the current item

        //assert the date within the item is equal to the expected string
    }

    @Test
    void markItemComplete()
    {
        //create an item that starts off as false

        //use function to mark it true

        //check to see if it's asserted true
    }

    @Test
    void saveList()
    {
        //create file through function


        //assert it exists in a certain location
    }

    @Test
    void loadList()
    {
        //may have to make an inventory to hold lists (not currently planned but probably being added)

        //make inventory of 2 lists

        //pass the string representing the title of the 2nd list into the function to find it


        //assert that the list found is the one we were looking for (test either through string passed or list from inventory
    }


}