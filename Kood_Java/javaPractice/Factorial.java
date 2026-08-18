package javaPractice;

public class Factorial {
    public static int calculateFactorial(int n){
        if(n < 0){
            return 0;
        }
        if (n <= 1){
            return 1;
        } else{
            return n * calculateFactorial(n-1);
        }
    }
    public static void main(String[] args) {
        Factorial calculator = new Factorial();
        System.out.println(calculator.calculateFactorial(5));
        System.out.println(calculator.calculateFactorial(-3));
    }
}
