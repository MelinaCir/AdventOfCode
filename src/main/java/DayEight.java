import org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// acc
// jmp
// nop
public class DayEight extends December {

    private List<String> instructions = new ArrayList<>();
    private int accumulator = 0;
    private int position = 0;
    private List<Integer> visitedPositions = new ArrayList<>();
    boolean alreadyVisited = false;

    void setUp(String inputFile) {
        try {
            final BufferedReader reader =
                    new BufferedReader(new InputStreamReader(ClassReader.class.getResourceAsStream(inputFile)));
            String line;

            while ((line = reader.readLine()) != null) {
                instructions.add(line);
            }
        }
        catch (IOException e) {
            System.out.println("Could not load file..");
        }
    }

    void part1() {

        readInstruction();

        System.out.println("Value of accumulator: " + accumulator);
    }

    private void readInstruction() {

        while (!alreadyVisited) {

            visitedPositions.add(position);
            String instruction = instructions.get(position);

            String[] line = instruction.split(" ");
            String operator = line[1].substring(0, 1);
            int num = Integer.parseInt(line[1].substring(1));

            if (line[0].equals("acc")) {

                if (operator.equals("+")) {
                    accumulator += num;
                }
                else {
                    accumulator -= num;
                }
                position++;
            }
            else if (line[0].equals("jmp")) {

                if (operator.equals("+")) {
                    position += num;
                    checkVisited();
                }
                else {
                    position -= num;
                    checkVisited();
                }
            }
            else {
                position++;
            }
        }
    }

    private void checkVisited() {

        for (int visited : visitedPositions) {

            if (position == visited) {
                alreadyVisited = true;
                break;
            }
        }
    }


    void part2() {

    }
}
