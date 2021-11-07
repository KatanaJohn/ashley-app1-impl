package baseline;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class RequirementCheckerTest {



    @Test
    void checkDescription()
    {
        RequirementChecker checker = new RequirementChecker();
        String test1 = "t";
        String test2 = "test";

        assertFalse(checker.checkDescription(test1));
        assertTrue(checker.checkDescription(test2));

    }

    @Test
    void checkDate()
    {
        RequirementChecker checker = new RequirementChecker();
        LocalDate date = null;
        String finalDate = checker.checkDueDate(date);

        assertEquals("N/A", finalDate);

        date = LocalDate.of(2021, Month.NOVEMBER, 6);
        finalDate = checker.checkDueDate(date);

        assertEquals("2021-11-06", finalDate);
    }



}