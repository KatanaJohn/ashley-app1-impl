package baseline;

public class Item {
    //items within a to do list should include a description and due date
    String description;
    String dueDate;
    boolean completion;


    //sets new item
    public Item(String description, String dueDate) {

        //use this. statements to set them

        //will set item completion to false from the start.
    }

    //gets description
    public String getDescription() {
        return description;
    }


    //gets due date
    public String getDueDate() {
        return dueDate;
    }

    public Item editItemDescription(Item item, String description)
    {
        //takes the item that a person wants to edit the description of

        //goes into the item and changes that description

        //returns it
        return item;
    }

    public Item editDueDate(Item item, String date)
    {
        //takes the item that a person wants to edit the description of

        //goes into the item and changes that dueDate

        //returns it
        return item;
    }

    private Item markItemComplete(Item item)
    {
        //changes boolean of item from false to true. Or true to false. Basically a switch.

        //return it
        return item;
    }


}