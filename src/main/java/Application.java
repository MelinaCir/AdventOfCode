/**
 * Class Application runs the chosen day of Advent of Code 2020.
 * Uncomment day to run it.
 *
 * @author MelinaCir
 * @version 0.1
 */
public class Application {

    public static void main(String[] args)
    {
//        new DayOne().solveDayOne();
//        new DayTwo().solveDayTwo();
//        new DayThree().solveDayThree();
//        new DayFour().solveDayFour();

        DayFive dayFive = new DayFive();
        dayFive.run("/seats.txt");

//        DaySix daySix = new DaySix();
//        daySix.run("/sixInput.txt");
    }
}
