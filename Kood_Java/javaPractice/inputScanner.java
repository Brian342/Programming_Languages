import java.util.Scanner;
public class inputScanner {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Your Name: ");
        String name = scanner.nextLine();
        // String name = scanner.next()  used when you dont want to read the nest space line

        System.out.print("Enter your Age: ");
        int age = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter your Favourite Color: ");
        String color = scanner.nextLine();

        System.out.println("What is your GPA?: ");
        double gpa = scanner.nextDouble();

        System.out.print("Are you a Student true/false: ");
        boolean isStudent = scanner.nextBoolean();

        System.out.println("Hello " + name);
        System.out.println("Your age is: "+ age + " Years old");
        System.out.println("Your GPA is: "+ gpa);

        if(isStudent){
            System.out.println("You are Enrolled as Student");
        }else{
            System.out.println("You are NOT enrolled as a Student!");
        }

        System.out.println("Your Favourite color is: "+ color);




        // System.out.println();

        scanner.close();
    }
    
}

