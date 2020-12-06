import org.objectweb.asm.ClassReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;

public class DayFive extends December {
    private List<String> boardingPasses = new ArrayList<>();
    private List<Integer> rows = new ArrayList<>();
    private List<Integer> columns = new ArrayList<>();
    final List<Integer> seatIds = new ArrayList<>();

    void setUp(String inputfile) {
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(ClassReader.class.getResourceAsStream(inputfile)));
            String line;
            while ((line = reader.readLine()) != null) {
                boardingPasses.add(line);
            }
        } catch (IOException e) {
            System.out.println("Could not load file..");
        }
    }

    void part1() {
        boardingPasses.forEach(boardingPass ->
        {
            final int row = checkRows(boardingPass);
            final int column = checkColumns(boardingPass);
            seatIds.add((row * 8) + column);
        });
        OptionalInt max = seatIds.stream().mapToInt(Integer::intValue).max();
        System.out.println("Max seat id: " + max.getAsInt());
    }

    private int checkRows(String boardingPass) {
        rows = createRows();
        final String substring = boardingPass.substring(0, 7);
        final char[] chars = substring.toCharArray();
        for (char aChar : chars) {
            if (aChar == 'F') {
                rows = rows.subList(0, rows.size() / 2);
            } else {
                rows = rows.subList(rows.size() / 2, rows.size());
            }
        }
        if (rows.size() == 1) {
            return rows.get(0);
        } else {
            return 0;
        }
    }

    private int checkColumns(String boardingPass) {
        columns = createColumns();
        final String substring = boardingPass.substring(7);
        final char[] chars = substring.toCharArray();
        for (char aChar : chars) {
            if (aChar == 'L') {
                columns = columns.subList(0, columns.size() / 2);
            } else {
                columns = columns.subList(columns.size() / 2, columns.size());
            }
        }
        if (columns.size() == 1) {
            return columns.get(0);
        } else {
            return 0;
        }
    }

    private List<Integer> createRows() {
        rows = new ArrayList<>();
        for (int i = 0; i <= 127; i++) {
            rows.add(i);
        }
        return rows;
    }


    private List<Integer> createColumns() {
        columns = new ArrayList<>();
        for (int i = 0; i <= 7; i++) {
            columns.add(i);
        }
        return columns;
    }

    void part2() {
        Collections.sort(seatIds);
        for (int i = 0; i < seatIds.size() - 1; i++) {
            if (seatIds.get(i) + 1 != seatIds.get(i + 1)) {
                System.out.println("Missing seat: " + (seatIds.get(i) + 1));
            }
        }
    }
}