package javaPractice;

import java.util.Scanner;

public class CalculatorSwitch {
    // make a calculator program using switch

    public static int calculator(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your operator(+,-,*,/,%,^): ");
        char op = scanner.nextLine().charAt(0);

        System.out.print("Enter First number: ");
        int a = scanner.nextInt();

        System.out.print("Enter Second number: ");
        int b = scanner.nextInt();


        return switch(op){
            case '+'-> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '^' -> (int) Math.pow(a, b);
            case '/' -> {
                if(b == 0){
                    System.out.println("Error! Division by zero");
                    yield 0;
                }else {
                    yield a / b;
                }
            }
            case '%' -> {
                if (b == 0){
                    System.out.println("Error: modulus by zero");
                    yield 0;
                }else yield a % b;
            }
            default -> {
                System.out.println(op + " Not an Operand");
                        yield -1;
            }
        };

    }
    public static void main(String[] args){
        int res = calculator();
        System.out.println("Result "+ res);



    }
}
