import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class DayFour solves the Passport Processing puzzles.
 *
 * @author MelinaCir
 * @version 0.1
 */
public class DayFour {

    private ArrayList<String> passports = new ArrayList<>();
    private ArrayList<String> passportFields = new ArrayList<>();
    private HashMap<String, String> allFields = new HashMap<>();

    private int validPassports = 0;
    private int passportCounter = 0; //TODO
    private int trulyValidPassports = 0;


    public void solveDayFour() {
        File file = new File("src/main/resources/passports.txt");

        try {
            Scanner scanner = new Scanner(file);
            readPassports(scanner);

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        System.out.println("---RESULTS---");
        System.out.println("Valid passports: " + validPassports);
        System.out.println("Number of passports: " + passportCounter);


    }

    private void readPassports(final Scanner scanner) {
        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            passports.add(line);
        }

        for (String string : passports) {
            if (!string.equals(" ")) {
                String[] fields = string.split("\\s+");

                for (String field : fields) {
                    passportFields.add(field);

                    String[] test = field.split(":");

                    allFields.put(test[0], test[1]);

                    System.out.println(allFields.keySet());
                }
            }
            else {
                passportCounter++;
                checkPassport();
                passportFields.clear();
                allFields.clear();
            }
            //TODO : Since the check is for a blank line, it is skipping the last passport unless another blank line
            // and input is added.
        }

    }

    private void checkPassport() {
        boolean cid = false;

        if (allFields.keySet().size() == 8) // Sets passport to valid if eight fields are present.
        {
            validPassports++;
        }
        else if (allFields.keySet().size() == 7) {

            System.out.println(allFields.get("cid"));

            if (allFields.containsKey("cid")) {
                System.out.println("Cid exists!");
            }
            for (String field : passportFields) {
                if (field.startsWith("cid")) // Checks if "cid" exists.
                {
                    cid = true;
                }
            }
            if (!cid) {
                validPassports++; // Sets passport to valid if "cid" was the only field missing.
            }
        }
    }

    //byr (Birth Year)
    //iyr (Issue Year)
    //eyr (Expiration Year)
    //hgt (Height)
    //hcl (Hair Color)
    //ecl (Eye Color)
    //pid (Passport ID)
    //cid (Country ID)
    private void checkPassportFields() {

        for (String field : passportFields) {
            String[] values = field.split(":");
            if (field.startsWith("byr")) {


            }
        }
    }
}
