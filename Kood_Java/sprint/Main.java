package sprint;
import sprint.ArrayInitializer;

public class Main {

        public static void main(String[] args) {
            ArrayInitializer initializer = new ArrayInitializer();
            int[] result = initializer.fillArray(5);
            for (int num : result) {
                System.out.print(num + " ");
            }
        }
    }

