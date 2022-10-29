import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException
    {
        Gson gson = new Gson();

        //parse email_names
        Reader namesReader = Files.newBufferedReader(Paths.get("src/main/resources/email_names.json"));
        EmailName[] names = gson.fromJson(namesReader, EmailName[].class);

        //parse email_numbers
        Reader numbersReader = Files.newBufferedReader(Paths.get("src/main/resources/email_numbers.json"));
        EmailNumber[] numbers = gson.fromJson(numbersReader, EmailNumber[].class);

        HashMap<String, Data> emails = processNames(names);
        processNumbers(numbers, emails);
        printResults(emails);
        System.out.println("done");


    }

    public static HashMap<String, Data> processNames(EmailName[] names)
    {
        HashMap<String, Data> emails = new HashMap<>();
        for (EmailName name: names) {
            String email = name.email;
            if (emails.containsKey(email))
            {
                emails.get(email).incrementNamesCount();
            }
            else{
                emails.put(email, new Data(email, name.first_name, name.last_name));
            }
        }
        return emails;
    }

    public static void processNumbers(EmailNumber[] numbers, HashMap<String, Data> emails)
    {

        for (EmailNumber number: numbers) {
            String email = number.email;
            if (emails.containsKey(email))
            {
                Data data = emails.get(email);
                if (data.namesCount != 1)
                {
                    emails.remove(email);
                }
                else if (data.hasNumber)
                {
                    emails.remove(email);
                }
                else
                {
                    data.setCcNumber(number.cc_number);
                    data.hasNumber = true;
                }
            }
        }
    }

    public static void printResults(HashMap<String, Data> emails)
    {
        for (Data email : emails.values()) {
            if (email.hasNumber)
            {
                System.out.println(email);
            }
        }
    }

}