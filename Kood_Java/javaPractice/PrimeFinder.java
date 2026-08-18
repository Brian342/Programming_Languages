package javaPractice;

import java.util.ArrayList;
import java.util.List;

public class PrimeFinder {
    public static boolean findPrimesUpTo(int number){
        if (number <= 1) {
            return false;

        }else if (number == 2){
            return true;
        }else if (number % 2 == 0){
            return false;
        }

        // checking for all divisor upto the square root of the number
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> findPrimes(int number){
        List<Integer> primes = new ArrayList<>();

        for(int i = 2; i <= number; i++){
            if (findPrimesUpTo(i)){
                primes.add(i);
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        int limit = 30;
        List <Integer>primes = PrimeFinder.findPrimes(limit);
        System.out.println(primes);
    }

}

