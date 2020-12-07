import org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DaySeven extends December {

    List<String> bagRules = new ArrayList<>();

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

    }

    void part2() {

    }
}
