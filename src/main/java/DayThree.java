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

    private int position = 0;
    private int counter = 0;

    public void solveDayThree()
    {
        double treeCounterR1D1 = readSlope(1, 0);
        double treeCounterR3D1 = readSlope(3, 0);
        double treeCounterR5D1 = readSlope(5, 0);
        double treeCounterR7D1 = readSlope(7, 0);
        double treeCounterR1D2 = readSlope(1, 1);

        System.out.println("---RESULTS---");
        System.out.println("Number of trees encountered with right 1, down 1: " + treeCounterR1D1);
        System.out.println("Number of trees encountered with right 3, down 1: " + treeCounterR3D1);
        System.out.println("Number of trees encountered with right 5, down 1: " + treeCounterR5D1);
        System.out.println("Number of trees encountered with right 7, down 1: " + treeCounterR7D1);
        System.out.println("Number of trees encountered with right 1, down 2: " + treeCounterR1D2);
        System.out.printf("Trees multiplied: %.0f\n", treeCounterR1D1 * treeCounterR3D1 * treeCounterR5D1 *
                treeCounterR7D1 * treeCounterR1D2);
    }

    private int readSlope(final int increase, final int row)
    {
        File file = new File("src/main/resources/trajectory.txt");
        counter = 0;
        position = 0;

        try
        {
            Scanner scanner = new Scanner(file);

            int lineWidth = scanner.nextLine().length(); // first line

            while (scanner.hasNext())
            {
                position = position + increase;

                if (row > 0) // For instances where the move is down two.
                {
                    scanner.nextLine();
                }

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
            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }

        return counter;
    }

    private void checkForTree(Scanner scanner)
    {
        String currentLine = scanner.nextLine();

        if (currentLine.charAt(position) == '#')
        {
            counter++;
        }
    }
}
