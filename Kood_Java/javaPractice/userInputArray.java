package javaPractice;

import java.util.Scanner;

public class userInputArray {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter # of foods: ");
        int size = scanner.nextInt();
        scanner.nextLine();

        String[] foods = new String[size];

        for(int i= 0; i<foods.length; i++){

            System.out.print("Enter a food: ");
            foods[i] = scanner.nextLine();
        }

        for(String food: foods){
            System.out.println(food);

        }

scanner.close();
    }
}

