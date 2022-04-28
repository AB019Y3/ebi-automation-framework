package africa.absa.ebi.Utilities;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class FunctionalLibrary {
    public static String generateMessageID() {
        Random random = new Random();
        return random.nextInt(100000) + "";
    }

    public static String getCurrentDate() {
        return LocalDateTime.now().toString();
    }

    public static String getFutureDate() {
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.plusDays(1).toString();
    }

    public static String getRandomString() {
        String currentDate = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(currentDate.length());
            char ranChar = currentDate.charAt(x);
            sb.append(ranChar);
        }
        return sb.toString();
    }

    public static String getAssistedType() {
        List<String> types = Arrays.asList("BRANCH", "CALL_CENTRE", "IN_STORE", "OTHER");
        Collections.shuffle(types);
        return types.get(0);
    }

    public static String getSelfServiceType() {
        List<String> types = Arrays.asList("APP", "WEB", "ALTERNATE_CHANNEL", "VOICE");
        Collections.shuffle(types);
        return types.get(0);
    }

    public static String getOwner() {
        List<String> types = Arrays.asList("ABSA_SOUTH_AFRICA", "PEP", "WFS", "STOKFELLA", "ABSA_TANZANIA");
        Collections.shuffle(types);
        return types.get(0);
    }

    public static String getApplicationName() {
        List<String> types = Arrays.asList("BANKING_APP", "USSD", "OSF", "IVR", "ASP", "APP", "ATM", "DPB");
        Collections.shuffle(types);
        return types.get(0);
    }

    public static Person getApplicant() {
        Fairy fairy = Fairy.create(Locale.ENGLISH);
        Person person = fairy.person();
        return person;
    }

    public static String getDOB(){
        Random random = new Random();
        int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2004, 1, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        return randomBirthDate.toString();
    }

    public static String getIDNumber(){
        return (long)(Math.random() * 10000000 * 1000000) + "";
    }

    @Test
    void tester() {
        long intArray;
        intArray = (long)(Math.random() * 10000000 * 1000000);
        System.out.println(intArray);

    }
}
