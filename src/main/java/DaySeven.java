import org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DaySeven extends December {

    List<String> bagRules = new ArrayList<>();
    List<String> bagsWithGold = new ArrayList<>();
    Set<String> validBagColors = new HashSet<>();

    void setUp(String inputFile) {
        try {
            final BufferedReader reader =
                    new BufferedReader(new InputStreamReader(ClassReader.class.getResourceAsStream(inputFile)));
            String line;

            while ((line = reader.readLine()) != null) {
                bagRules.add(line);
            }

        }
        catch (IOException e) {
            System.out.println("Could not load file..");
        }
    }

    void part1() {

        bagRules.forEach(bag -> checkForGold(bag, "shiny gold"));

        for (int i = 0; i < bagsWithGold.size(); i++) {

            for (String bag : bagRules) {
                checkForGold(bag, bagsWithGold.get(i));
            }
        }

        bagsWithGold.forEach(color -> validBagColors.add(color));

        System.out.println("Number of bag colors: " + validBagColors.size());

    }

    private void checkForGold(String bag, String color) {
        String[] bags = bag.split("bags contain|,");

        for (int i = 1; i < bags.length; i++) {

            if (bags[i].contains(color)) {
                bagsWithGold.add(bags[0].trim());
            }
        }
    }

    void part2() {

    }
}
