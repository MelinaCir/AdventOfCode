import org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class DayNine solves the puzzles from day nine.
 *
 * @author MelinaCir
 * @version 0.1
 */
public class DayNine extends December {

    private List<Long> numbers = new ArrayList<>();
    private List<Long> countingNumbers = new ArrayList<>();
    private int next = 25;
    private long invalidNum = 0;
    private boolean correctSum = false;

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
        invalidNum = numbers.get(next);
        System.out.println("First invalid number is: " + invalidNum);
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

        for (Long num : numbers) {

            if (!correctSum) {
                countingNumbers.clear();
                countNumbers(num);
            }
        }

        findWeakness();
    }

    private void countNumbers(Long num) {

        if (num < invalidNum) {

            countingNumbers.add(num);
            int position = numbers.indexOf(num);

            for (int i = position + 1; i < numbers.size(); i++) {

                long current = numbers.get(i);
                countingNumbers.add(current);

                num += current;

                if (num == invalidNum) {
                    correctSum = true;
                    break;
                }
                else if (num > invalidNum) {
                    break;
                }
            }
        }
    }

    private void findWeakness() {

        countingNumbers.sort(null);

        Long smallest = countingNumbers.get(0);
        Long largest = countingNumbers.get(countingNumbers.size() -1);

        System.out.println("Encryption weakness is: " + (smallest + largest));
    }
}
