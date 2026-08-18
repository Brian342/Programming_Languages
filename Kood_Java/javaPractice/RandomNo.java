package javaPractice;


import java.util.Random;
import java.util.Scanner;

public class RandomNo {
    // Number guessing game

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        Random randomNo = new Random();

        int guess;
        int randomNumber = randomNo.nextInt(1, 11);
        int attempts;



        attempts = 0;
        do{
            System.out.println("Welcome to number guessing game");
            System.out.print("Enter your Guessing No!: ");
            guess = sc.nextInt();
            attempts++;

            if(guess < randomNumber){
                System.out.println("Number is too low");
            }else if(guess > randomNumber){
                System.out.println("Number is too high");
            } else
                System.out.println("Number is correct");
                System.out.println("# of attempts" + attempts);

            for(int i = 0; i < attempts; i++) {
                if(attempts == 3) {
                    System.out.println("The random no is: " + randomNo);
                    break;
                }
            }

        }
        while (guess != randomNumber);

        sc.close();
    }

}

