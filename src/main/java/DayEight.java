import org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DayEight solves the puzzles from day eight.
 *
 * @author MelinaCir
 * @version 0.1
 */
public class DayEight extends December {

    private List<String> instructions = new ArrayList<>();
    private List<Integer> visitedPositions = new ArrayList<>();
    private List<Integer> changedPositions = new ArrayList<>();

    private int accumulator = 0;
    private int position = 0;

    private boolean alreadyVisited = false;
    private boolean jmpNopChanged = false;


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

        System.out.println("Value of accumulator part 1: " + accumulator);
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

    // PART TWO
    void part2() {
        reset();
        changeInstructions();

        System.out.println("Value of accumulator part 2: " + accumulator);

    }

    private void changeInstructions() {

        while (!alreadyVisited && position < instructions.size()) {

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
            else if (num != 0) {

                checkJump(line, operator, num);
            }
            else {
                position++;
            }
        }

        if (position < instructions.size()) {
            reset();
            changeInstructions();
        }
    }

    private void checkJump(String[] line, String operator, int num) {

        if (!jmpNopChanged) {

            if (!checkChanged()) {
                changedPositions.add(position);
                jmpNopChanged = true;

                if (line[0].equals("jmp")) { // Jmp is treated as nop
                    position++;
                }
                else {
                    if (operator.equals("+")) { // nop is treated as jmp.
                        position += num;
                        checkVisited();
                    }
                    else {
                        position -= num;
                        checkVisited();
                    }
                }
            }
            else // TODO
            {
                if (line[0].equals("jmp")) {

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
        else {
            if (line[0].equals("jmp")) {

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


    private boolean checkChanged() {

        for (int changed : changedPositions) {
            if (position == changed) {
                return true;
            }
        }
        return false;
    }

    private void reset() {
        visitedPositions.clear();
        alreadyVisited = false;
        jmpNopChanged = false;
        position = 0;
        accumulator = 0;
    }
}
