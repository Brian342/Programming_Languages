package javaPractice;

public class GCDRecursive {
    public static int gcd(int a, int b) {
        if (a == 0 && b == 0) {
            return 0;
        } else if (b == 0) {
            return Math.abs(a);
        } else {
            return gcd(b, a % b);
        }
    }

    public static void main(String[] args) {
        GCDRecursive calculator = new GCDRecursive();
        System.out.println(calculator.gcd(48, 18));
        System.out.println(calculator.gcd(100, 75));
    }
}

