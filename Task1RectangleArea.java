import java.util.Scanner;

public class Task1RectangleArea {

    // Method overloading: integer inputs return an integer.
    public static int calculateArea(int length, int width) {
        return length * width;
    }

    // Method overloading: double inputs return a double.
    public static double calculateArea(double length, double width) {
        return length * width;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter rectangle length and width (integers): ");
        int intLength = scanner.nextInt();
        int intWidth = scanner.nextInt();
        System.out.println("Integer area: " + calculateArea(intLength, intWidth));

        System.out.print("Enter rectangle length and width (doubles): ");
        double doubleLength = scanner.nextDouble();
        double doubleWidth = scanner.nextDouble();
        System.out.println("Double area: " + calculateArea(doubleLength, doubleWidth));

        scanner.close();
    }
}
