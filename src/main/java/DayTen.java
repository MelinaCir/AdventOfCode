import org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * DayTen solves the puzzles of day ten.
 *
 * @author MelinaCir
 */
public class DayTen extends December {

    private List<Integer> adapters = new ArrayList<>();
    private int oneVolt = 0;
    private int threeVolt = 1;


    void setUp(String inputFile) {

        try {
            final BufferedReader reader =
                    new BufferedReader(new InputStreamReader(ClassReader.class.getResourceAsStream(inputFile)));
            String line;

            while ((line = reader.readLine()) != null) {
                adapters.add(Integer.parseInt(line));
                adapters.sort(null);
            }
        }
        catch (IOException e) {
            System.out.println("Could not load file..");
        }
    }

    void part1() {

        findVoltDifference();

        System.out.println("One volt differences: " + oneVolt);
        System.out.println("Three volt differences: " + threeVolt);
        System.out.println("Number of differences multiplied: " + oneVolt * threeVolt);
    }

    private void findVoltDifference() {

        if (adapters.get(0) == 1) {
            oneVolt++;
        }
        else if (adapters.get(0) == 3) {
            threeVolt++;
        }

        for (int i = 0; i < adapters.size() - 1; i++) {

            int voltDifference = adapters.get(i + 1) - adapters.get(i);

            if (voltDifference == 1) {
                oneVolt++;
            }
            else if (voltDifference == 3) {
                threeVolt++;
            }
        }
    }

    void part2() {

    }
}
