package javaPractice;

import java.util.Scanner;

public class searchArray {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int target;
        boolean isFound = false;

        int[] numbers = {1, 9, 2, 8, 3, 5, 4};
        String[] fruits = {"apple", "orange", "banana", "mango"};

        // user input to check for element target
        System.out.print("Enter # to look if its present in the system: ");
        target = sc.nextInt();
        System.out.print("Enter a fruit to check if its in the system: ");
        String fruit = sc.next();

        // performing liner searching
        for(int i = 0; i< numbers.length; i++){
            if(target == numbers[i]){
                System.out.println( target + " found at index: " + i);
                isFound = true;
                break;
            }
        }
        for(int j=0; j<fruits.length; j++){
            if(fruit.equals(fruits[j])){
                System.out.println(fruit + " found at index: " + j);
                isFound = true;
                break;
            }
        }
        if(!isFound){
            System.out.println("Element not found in the system!");
        }

    }
}
