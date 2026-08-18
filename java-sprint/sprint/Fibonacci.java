package sprint;

public class Fibonacci {
    public static int calculateFibonacci(int n){
        if(n < 0){
            return -1;
        }
        if(n < 2){
            return n;
        }else{
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }
    
    
}
