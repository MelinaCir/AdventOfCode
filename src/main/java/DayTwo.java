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

    private int firstValue = 0;
    private int secondValue = 0;
    private char letterToTest;
    private ArrayList<Character> passwordChars;
    private int validCounter = 0;
    private int validCounterTwo = 0;

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
        System.out.println("Number of valid passwords according to second puzzle policy: " + validCounterTwo);
    }

    private void readPasswords(final Scanner scanner)
    {
        while (scanner.hasNext())
        {
            String minMax = scanner.next(); // The first two numbers.
            String letter = scanner.next(); // The letter that is needed.
            String password = scanner.nextLine(); // The given password.

            formatInputs(minMax, letter, password);
            checkPassword();
            checkPasswordAgain();
        }
    }

    private void formatInputs(final String minMax, final String letter, String password)
    {
        String[] minMaxValue = minMax.split("-", 2);

        firstValue = Integer.parseInt(minMaxValue[0]);
        secondValue = Integer.parseInt(minMaxValue[1]);

        letterToTest = letter.charAt(0);

        passwordChars = new ArrayList<Character>();
        password = password.replaceAll("\\s", "");

        for (int i = 0; i < password.length(); i++)
        {
            passwordChars.add(password.charAt(i));
        }
    }

    private void checkPassword()
    {
        int counter = 0;

        for (char character : passwordChars)
        {
            if (character == letterToTest)
            {
                counter++;
            }
        }

        if (counter >= firstValue && counter <= secondValue)
        {
            validCounter++;
        }
    }

    private void checkPasswordAgain()
    {
        int occurrences = 0;

        if (passwordChars.size() >= secondValue) {

            if (letterToTest == passwordChars.get(firstValue - 1))
            {
                occurrences++;
            }

            if (letterToTest == passwordChars.get(secondValue - 1))
            {
                occurrences++;
            }
        }

        if (occurrences == 1)
        {
            validCounterTwo++;
        }
    }

}
