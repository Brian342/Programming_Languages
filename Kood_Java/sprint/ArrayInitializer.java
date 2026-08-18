package sprint;

public class ArrayInitializer {
    public int[] fillArray(int max){
        int[] arr1 = new int[max];

        // Fill the array with values from 1 to max
        for (int i = 0; i < max; i++) {
            arr1[i] = i + 1;
        }

        return arr1;
    }
}
