import org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySix extends December {

    List<String> declarationForms = new ArrayList<>();

    void setUp(String inputfile) {
        try {
            final BufferedReader reader =
                    new BufferedReader(new InputStreamReader(ClassReader.class.getResourceAsStream(inputfile)));
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    stringBuilder.append(line).append(" ");
                }
                else {
                    declarationForms.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            }
            declarationForms.add(stringBuilder.toString());

        }
        catch (IOException e) {
            System.out.println("Could not load file..");
        }
    }

    void part1() {
        int counter = 0;
        for (String form : declarationForms) {
            Set<Character> yesAnswers = new HashSet<>();

            char[] chars = form.toCharArray();

            for (char yes : chars) {
                if (yes != ' ') {
                    yesAnswers.add(yes);
                }
            }
            counter = counter + yesAnswers.size();
        }

        System.out.println("Number of total 'yes': " + counter);
    }

    void part2() {
        int counter = 0;
        for (String form : declarationForms) {
            counter += countValidYes(form);
        }

        System.out.println("Number of total 'yes' part2: " + counter);
    }

    private int countValidYes(String f) {
        String[] group = f.split(" ");
        int yesCounter = 0;

        if (group.length == 1) {
            return group[0].length();
        }
        else {
            String firstPerson = group[0];

            char[] chars = firstPerson.toCharArray(); // f

            for (char c : chars) {
                int counter = 0;

                for (int i = 0; i < group.length; i++) {
                    char[] nextAnswers = group[i].toCharArray();

                    for (char y : nextAnswers) {
                        if (y == c) {
                            counter++;
                            break;
                        }
                    }
                }
                if (counter == group.length) {
                    yesCounter++;
                }
            }
        }

        return yesCounter;
    }
}
