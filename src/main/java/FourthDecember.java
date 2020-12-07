
import org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

public class FourthDecember extends December {
    private final ArrayList<String> passports = new ArrayList<>();
    private final List<String> rules = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

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
                    passports.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            }
            passports.add(stringBuilder.toString());
        }
        catch (IOException e) {
            System.out.println("Could not load file..");
        }
    }

    void part1() {
        final List<String> collect = passports.stream().filter(this::match).collect(toList());
        System.out.println("Valid passports par 1: " + collect.size());
    }

    private boolean match(String passport) {
        AtomicInteger counter = new AtomicInteger();
        rules.forEach(rule -> {
            if (passport.contains(rule)) {
                counter.getAndIncrement();
            }
        });
        return counter.get() == 7;
    }

    void part2() {
        final List<String> collect = passports.stream().filter(this::match2).collect(toList());
        System.out.println("Valid passports par 2: " + collect.size());
    }

    private boolean match2(String passport) {
        boolean byrRule = false;
        boolean iyrRule = false;
        boolean eyrRule = false;
        boolean hgtRule = false;
        boolean hclRule = false;
        boolean eclRule = false;
        boolean pidRule = false;
        for (String rule : rules) {
            if (passport.contains(rule)) {
                switch (rule) {
                    case "byr":
                        byrRule = byrRule(passport);
                    case "iyr":
                        iyrRule = iyrRule(passport);
                    case "eyr":
                        eyrRule = eyrRule(passport);
                    case "hgt":
                        hgtRule = hgtRule(passport);
                    case "hcl":
                        hclRule = hclRule(passport);
                    case "ecl":
                        eclRule = eclRule(passport);
                    case "pid":
                        pidRule = pidRule(passport);
                }
            }
        }
        return byrRule && iyrRule && eyrRule && hgtRule && hclRule && eclRule && pidRule;
    }

    private boolean byrRule(String passport) {
        final String[] split = passport.split("byr:");
        final String[] s = split[1].split(" ");
        String birthYear = s[0];
        if (birthYear.length() == 4) {
            return parseInt(birthYear) <= 2002 && parseInt(birthYear) >= 1920;
        }
        return false;
    }

    private boolean iyrRule(String passport) {
        final String[] split = passport.split("iyr:");
        if (split.length > 1) {
            final String[] s = split[1].split(" ");
            final String issueYear = s[0];
            if (issueYear.length() == 4) {
                return parseInt(issueYear) <= 2020 && parseInt(issueYear) >= 2010;
            }
        }
        return false;
    }

    private boolean eyrRule(String passport) {
        final String[] split = passport.split("eyr:");
        if (split.length > 1) {
            final String[] s = split[1].split(" ");
            final String expirationYear = s[0];
            if (expirationYear.length() == 4) {
                return parseInt(expirationYear) <= 2030 && parseInt(expirationYear) >= 2020;
            }
        }
        return false;
    }

    private boolean hgtRule(String passport) {
        final String[] split = passport.split("hgt:");
        if (split.length > 1) {
            final String[] s = split[1].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
            String height = s[0];
            if (s[1].length() > 2) {
                final String cmOrIn = s[1].substring(0, 2);
                if (cmOrIn.equals("cm")) {
                    return parseInt(height) <= 193 && parseInt(height) >= 150;
                }
                else if (cmOrIn.equals("in")) {
                    return parseInt(height) <= 76 && parseInt(height) >= 59;
                }
            }
        }
        return false;
    }

    private boolean hclRule(String passport) {
        boolean matches = false;
        final String[] split = passport.split("hcl:");
        if (split.length > 1) {
            final String[] s = split[1].split(" ");
            String hairColour = s[0];
            matches = hairColour.matches("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");
        }
        return matches;
    }

    private boolean eclRule(String passport) {
        final String[] split = passport.split("ecl:");
        if (split.length > 1) {
            final String[] s = split[1].split(" ");
            final String eyeColour = s[0];
            return eyeColour.equals("amb") || eyeColour.equals("blu") || eyeColour.equals("brn") || eyeColour.equals(
                    "gry")
                    || eyeColour.equals("grn") || eyeColour.equals("hzl") || eyeColour.equals("oth");
        }
        return false;
    }

    private boolean pidRule(String passport) {
        final String[] split = passport.split("pid:");
        if (split.length > 1) {
            final String[] s = split[1].split(" ");
            final String pid = s[0];
            if (pid.length() == 9) {
                return pid.matches("\\d+");
            }
        }
        return false;
    }
}