import java.util.Scanner;

public class Task3RecursiveDigitSum {

    public static int sumDigits(long x) {
        if (x < 10) {
            return (int) x;
        }
        return (int) (x % 10) + sumDigits(x / 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a natural number: ");
        long x = scanner.nextLong();

        if (x < 0) {
            System.out.println("Error: Enter a non-negative integer.");
        } else {
            System.out.println("Sum of digits: " + sumDigits(x));
        }

        scanner.close();
    }
}
