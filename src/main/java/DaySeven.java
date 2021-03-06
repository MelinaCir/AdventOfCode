import org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Class DaySeven solves the luggage puzzles of day seven.
 *
 * @author MelinaCir
 * @version 0.1
 */
public class DaySeven extends December {

    List<String> bagRules = new ArrayList<>();
    List<String> bagsWithGold = new ArrayList<>();
    Set<String> validBagColors = new HashSet<>();
    private int bagCounter = 0;

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

        findBags("shiny gold", 1);

        System.out.println("Number of bags inside shiny gold: " + bagCounter);
    }

    private void findBags(String color, int parent) {

        for (String rule : bagRules) {

            if (rule.startsWith(color)) {
                countBags(rule, parent);
                break;
            }
        }
    }


    private void countBags(String rule, int parent) {

        String[] rules = rule.split("\\s*contain\\s*|\\s*bag,\\s*|\\s*bags,\\s*|\\s*bag\\.\\s*|\\s*bags\\.\\s*");

        for (int i = 1; i < rules.length; i++) {

            String nextBag = rules[i];
            try {
                int numberOfBags = Integer.parseInt(nextBag.substring(0, 1));
                int totalBags = numberOfBags * parent;

                bagCounter += totalBags;

                findBags(nextBag.substring(2), totalBags);
            }
            catch (NumberFormatException ignored) {
            }

        }
    }
}
