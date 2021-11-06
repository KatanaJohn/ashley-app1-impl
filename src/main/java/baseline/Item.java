/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 John Ashley
 */


package baseline;

import java.util.ArrayList;
import java.util.List;

public class Item {
    //items within a to do list should include a description and due date
    String description;
    String dueDate;
    boolean completion;
    public static final List<Item> toDoList = new ArrayList<>();


    //sets new item
    public Item() {

        //use this. statements to set them
        this.description = "";
        this.dueDate = "";
        this.completion = false;

        //will set item completion to false from the start.
    }


    public Item(String description, String dueDate, boolean completion) {

        //use this. statements to set them
        this.description = description;
        this.dueDate = dueDate;
        this.completion = completion;

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

    public void setCompletionStatus(boolean completionStatus) {
        this.completion = completionStatus;
    }

    public static List<Item> getToDoList()
    {
        return toDoList;
    }

    public static void removeItem(Item item){
        //remove the passed item from the toDoList
        toDoList.remove(item);
    }




}