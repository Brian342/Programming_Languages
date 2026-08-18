package sprint;

public class ArrayInitializer {

    public int[] fillArray(int max) {
        // 1. MUST check boundary condition FIRST
        if (max < 1) {
            return new int[0];
        }

        // 2. Only allocate after validating max >= 1
        int[] result = new int[max];

        for (int i = 0; i < max; i++) {
            result[i] = i + 1;
        }

        return result;
    }
}
