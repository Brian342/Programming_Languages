package javaPractice;

public class Fibonacci {
    public static int calculateFibonacci(int n){
        if(n < 0){
            return -1;
        }
        if(n < 2){
            return n;
        }else{
            return calculateFibonacci(n-1) + calculateFibonacci(n-2);
        }
    }
    public static void main(String[] args) {
        Fibonacci calculator = new Fibonacci();
        System.out.println(calculator.calculateFibonacci(6));
        System.out.println(calculator.calculateFibonacci(-3));
    }
}
