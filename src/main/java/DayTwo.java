import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class DayTwo solves the Password Philosophy problem.
 *
 * @author MelinaCir
 * @version 0.1
 */
public class DayTwo {

    private String minMax;
    private String letter;
    private String password;
    private int validCounter = 0;

    public void solveDayTwo()
    {
        File file = new File("src/main/resources/passwords.txt");

        try
        {
            Scanner scanner = new Scanner(file);
            readPasswords(scanner);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }

        System.out.println("--- RESULTS---");
        System.out.println("Number of valid passwords according to first puzzle policy: " + validCounter);
    }

    private void readPasswords(final Scanner scanner)
    {
        while (scanner.hasNext())
        {
            minMax = scanner.next(); // The first two numbers.
            letter = scanner.next(); // The letter that is needed.
            password = scanner.nextLine(); // The given password.

            checkPassword();
        }
    }

    private void checkPassword()
    {
        String[] minMaxValue = minMax.split("-", 2);

        int minValue = Integer.parseInt(minMaxValue[0]);
        int maxValue = Integer.parseInt(minMaxValue[1]);

        char letterToTest = letter.charAt(0);
        ArrayList<Character> passwordChars = new ArrayList<Character>();

        int counter = 0;

        for (int i = 0; i < password.length(); i++)
        {
            passwordChars.add(password.charAt(i));
        }

        for (char character : passwordChars)
        {
            if (character == letterToTest)
            {
                counter++;
            }
        }

        if (counter >= minValue && counter <= maxValue)
        {
            validCounter++;
        }
    }

}
