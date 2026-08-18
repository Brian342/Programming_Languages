package sprint;

public class ArrayAdder {
    public static int[] concatArrays(int[] arr1, int[] arr2) {
        // Handle null inputs gracefully
        if (arr1 == null) return arr2 != null ? arr2.clone() : new int[0];
        if (arr2 == null) return arr1.clone();

        // Calculate combined length and create destination array
        int totalLength = arr1.length + arr2.length;
        int[] result = new int[totalLength];

        // Copy elements from arr1
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i];
        }

        // Copy elements from arr2 starting right after arr1
        for (int i = 0; i < arr2.length; i++) {
            result[arr1.length + i] = arr2[i];
        }

        return result;
    }
}