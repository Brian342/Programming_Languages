package javaPractice;

import java.sql.SQLOutput;
import java.util.Scanner;

public class switchesJava {
    public static String getDay(String day){
        if(day==null || day.isEmpty()){
            return "Unknown";
        }
        return switch (day.toLowerCase()){
            case "monday", "tuesday", "wednesday", "thursday"->
                    "It is the weekday😭😭";
            case "friday", "faturday", "sunday" ->
                    "It is the weekend😁😁";
            default -> day + " It is not a day";
        };

    }
    public static void main(String[] args){
        String value;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your day: ");
        value = scanner.nextLine();

        String res = getDay(value);
        System.out.println(res);
        scanner.close();
    }

}