import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class DayThree solves the Toboggan Trajectory puzzle.
 *
 * @author MelinaCir
 * @version 0.1
 */
public class DayThree {

    private int treeCounter = 0;
    private int position = 0;

    public void solveDayThree()
    {
        File file = new File("src/main/resources/trajectory.txt");

        try
        {
            Scanner scanner = new Scanner(file);
            readSlope(scanner);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }

        System.out.println("---RESULTS---");
        System.out.println("Number of trees encountered: " + treeCounter);
    }

    private void readSlope(final Scanner scanner)
    {
        int lineWidth = scanner.nextLine().length(); // first line

        while (scanner.hasNext())
        {
            position = position + 3;

            if (position < lineWidth)
            {
                checkForTree(scanner);
            }
            else
            {
                position = position - 31;

                checkForTree(scanner);
            }
        }
    }

    private void checkForTree(Scanner scanner)
    {
        String currentLine = scanner.nextLine();

        if (currentLine.charAt(position) == '#')
        {
            treeCounter++;
        }
    }
}
