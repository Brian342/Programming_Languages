package sprint;

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
    
}
