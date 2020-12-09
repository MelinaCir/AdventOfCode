import org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DayNine solves the puzzles from day nine.
 *
 * @author MelinaCir
 * @version 0.1
 */
public class DayNine extends December {

    private List<Long> numbers = new ArrayList<>();
    private int next = 25;

    void setUp(String inputFile) {

        try {
            final BufferedReader reader =
                    new BufferedReader(new InputStreamReader(ClassReader.class.getResourceAsStream(inputFile)));
            String line;

            while ((line = reader.readLine()) != null) {
                numbers.add(Long.parseLong(line));
            }
        }
        catch (IOException e) {
            System.out.println("Could not load file..");
        }
    }

    void part1() {

        while (checkNextNum()) {
            next++;
        }

        System.out.println("Firt invalid number is: " + numbers.get(next));
    }

    private boolean checkNextNum() {

        boolean valid = false;
        long nextNum = numbers.get(next);

        for (int i = next - 25; i < next; i++) {

            long first = numbers.get(i);

            for (int j = i + 1; j < next; j++) {

                long second = numbers.get(j);

                if (first + second == nextNum) {
                    valid = true;
                }
            }
        }

        return valid;
    }

    void part2() {

    }
}
