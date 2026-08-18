package javaPractice;

import java.util.Scanner;
public class weightConversion{
    public static void main(String[] args){
        // WEIGHT CONVERSION PROGRAM

        Scanner scanner = new Scanner(System.in);

        double weight;
        double newWeight;
        int choice;

        // Welcoming Message
        System.out.println("Welcome to Weight Conversion Program");
        // Capture User input
        System.out.print("Enter Your weight: ");
        weight = scanner.nextDouble();

        System.out.print("""
                Enter your Choice of conversion
                 1 for lbs to kgs
                 2 for kgs to lbs: """);
        choice = scanner.nextInt();

        // Option 1 converting lbs to kgs
        if (choice == 1){
            System.out.println("You selected conversion of lbs to kgs");
            newWeight = weight / .45359237;
            System.out.println("Your weight in lbs is: "+ newWeight);


            // option 2 converting kgs to lbs

        } else if (choice == 2) {
            System.out.println("You selected conversion of kgs to lbs");
            newWeight = weight * 2.20462;
            System.out.println("Your weight in kgs is: "+ newWeight);

        } else{
            System.out.println("Invalid choice please select between 1 or 2!");
        }


    }
}