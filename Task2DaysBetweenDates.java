import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Task2DaysBetweenDates {

    public static long daysBetween(int year_from, int month_from, int day_from,
                                   int year_to, int month_to, int day_to) {
        LocalDate from = LocalDate.of(year_from, month_from, day_from);
        LocalDate to = LocalDate.of(year_to, month_to, day_to);
        return Math.abs(ChronoUnit.DAYS.between(from, to));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter first date (year month day): ");
            int year_from = scanner.nextInt();
            int month_from = scanner.nextInt();
            int day_from = scanner.nextInt();

            System.out.print("Enter second date (year month day): ");
            int year_to = scanner.nextInt();
            int month_to = scanner.nextInt();
            int day_to = scanner.nextInt();

            long days = daysBetween(year_from, month_from, day_from,
                                    year_to, month_to, day_to);
            System.out.println("Number of days between the dates: " + days);
        } catch (DateTimeException e) {
            System.out.println("Invalid date: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
