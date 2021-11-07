package baseline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequirementChecker {


    public boolean checkDescription(String newDescription)
    {
        if(newDescription.length() > 1 && newDescription.length() < 256)
        {
            return true;
        }
        return false;
    }

    public String checkDueDate(LocalDate date)
    {
        if(date == null)
        {
            return "N/A";
        }
        else
        {
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }



}
