package baseline;

public class Item {
    //items within a to do list should include a description and due date
    String description;
    String dueDate;
    boolean completion;


    //sets new item
    public Item() {

        //use this. statements to set them
        this.description = "";
        this.dueDate = "";
        this.completion = false;

        //will set item completion to false from the start.
    }

    public Item(String description, String dueDate, boolean complete) {

        //use this. statements to set them
        this.description = description;
        this.dueDate = dueDate;
        this.completion = complete;

        //will set item completion to false from the start.
    }


    public String getDescription() {
        return description;
    }

    //gets description
    public void setDescription(String description) {
        this.description = description;
    }


    //gets due date
    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String DueDate)
    {
        this.dueDate = DueDate;
    }

    public boolean getCompletionStatus() {
        return completion;
    }

    public void setCompleteStatus(boolean completionStatus) {
        this.completion = completionStatus;
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