import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class DayOne solves the Report Repair problem of day one.
 *
 * @author MelinaCir
 * @version 0.1
 */
public class DayOne {

    public void solveDayOne()
    {
        File file = new File("src/main/resources/reportrepair.txt");
        ArrayList<Integer> reportList = new ArrayList<Integer>();

        try
        {
            Scanner scanner = new Scanner(file);
            reportList = readFile(scanner);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }

        find2020(reportList);
        find2020by3(reportList);
    }

    private ArrayList<Integer> readFile(Scanner scanner)
    {
        ArrayList<Integer> reportList = new ArrayList<Integer>();

        while (scanner.hasNext())
        {
            int entry = scanner.nextInt();

            reportList.add(entry);
        }

        return reportList;
    }

    private void find2020(ArrayList<Integer> entriesList)
    {
        int entryOne = 0;
        int entryTwo = 0;
        for (Integer entry : entriesList)
        {
            for (int i = 1; i < entriesList.size(); i++)
            {
                if (entry + entriesList.get(i) == 2020)
                {
                    entryOne = entry;
                    entryTwo = entriesList.get(i);
                }
            }
        }

        System.out.println("Entry: " + entryOne + " and entry: " + entryTwo + " equals 2020!");

        int sum = entryOne * entryTwo;

        System.out.println("Sum of " + entryOne + " and " + entryTwo + " is: " + sum);
    }

    private void find2020by3(ArrayList<Integer> entriesList)
    {
        int entryOne = 0;
        int entryTwo = 0;
        int entryThree = 0;
        int sum;

        for (Integer entry : entriesList)
        {
            for (int i = 1; i < entriesList.size(); i++)
            {
                int currenti = entriesList.get(i);

                for (int j = 2; j < entriesList.size(); j++)
                {
                    int currentj = entriesList.get(j);

                    if (entry + currenti + currentj == 2020)
                    {
                        entryOne = entry;
                        entryTwo = currenti;
                        entryThree = currentj;
                    }

                }

            }
        }

        System.out.println(entryOne + " plus " + entryTwo + " plus " + entryThree + " equals 2020!");

        sum = entryOne * entryTwo * entryThree;

        System.out.println("Sum is: " + sum);
    }
}
