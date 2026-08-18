import java.util.Scanner;

public class RectangleCalculation{
    public static void main(String[] args){
        // Calculate the area of a Rectangle

        Scanner scanner = new Scanner(System.in);

        // Enter the width
        System.out.print("Enter the width: ");
        double width = scanner.nextDouble();

        // Enter the length
        System.out.print("Enter the Length: ");
        double length = scanner.nextDouble();

        // calculate the perimeter
        double perimeter = 2 * (length + width);

        // calculate the area
        double area = length * width;

        System.out.println("The Rectangle perimeter is: "+ perimeter + "cm²");
        System.out.println("The Rectangle area is: "+ area + "cm²");

        scanner.close();

    }

}