import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    EmailName[] givenEmailNames()
    {
        EmailName[] names = new EmailName[4];
        names[0] = new EmailName("John", "Smith", "js@gmail.com");
        names[1] = new EmailName("J", "Smith", "js@gmail.com");
        names[2] = new EmailName("John", "Smith", "johnsmith@gmail.com");
        names[3] = new EmailName("Albert", "Willis", "aw@gmail.com");
        return names;
    }

    EmailNumber[] givenEmailNumbers()
    {
        EmailNumber[] numbers = new EmailNumber[5];
        numbers[0] = new EmailNumber("js@gmail.com", "1");
        numbers[1] = new EmailNumber("abcd@gmail.com", "2");
        numbers[2] = new EmailNumber("abcd@gmail.com", "3");
        numbers[3] = new EmailNumber("johnsmith@gmail.com", "4");
        numbers[4] = new EmailNumber("notinnames@gmail.com", "5");
        return numbers;
    }

    @Test
    void processNames()
    {
        //given
        EmailName[] names = givenEmailNames();

        //when
        HashMap<String, Data> results = Main.processNames(names);

        //then
        Data doubleEmail = results.get("js@gmail.com");
        Data singleEmail = results.get("johnsmith@gmail.com");
        assertEquals(doubleEmail.namesCount, 2);
        assertEquals(singleEmail.namesCount, 1);

    }

    @Test
    void processNumbers()
    {
        //given
        EmailName[] names = givenEmailNames();
        EmailNumber[] numbers = givenEmailNumbers();

        //when
        HashMap<String, Data> results = Main.processNames(names);
        Main.processNumbers(numbers, results);

        //then
        assertFalse(results.containsKey("js@gmail.com"));
        assertFalse(results.containsKey("notinnames@gmail.com"));
        assertFalse(results.containsKey("abcd@gmail.com"));
        Data singleEmail = results.get("johnsmith@gmail.com");
        assertEquals(singleEmail.ccNumber, "4");
        assertTrue(singleEmail.hasNumber);

    }

}