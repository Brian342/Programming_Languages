import java.util.Scanner;
public class positiveEven {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Value: ");
        int value = scanner.nextInt();

        System.out.println(value > 0 && (value % 2 == 0));

    }
}
