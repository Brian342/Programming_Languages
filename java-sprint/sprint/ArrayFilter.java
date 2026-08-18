package sprint;

import java.util.ArrayList;
import java.util.List;

public class ArrayFilter {

    public int[][] filterBySum(int[][] array, int value) {
        if (array == null) {
            return new int[0][];
        }

        List<int[]> validSubarrays = new ArrayList<>();

        for (int[] row : array) {
            if (row != null) {
                int sum = 0;
                for (int num : row) {
                    sum += num;
                }

                // Keep rows where the sum is greater than or equal to the target value
                if (sum >= value) {
                    validSubarrays.add(row);
                }
            }
        }

        // Convert the List back into a 2D array
        return validSubarrays.toArray(new int[validSubarrays.size()][]);
    }
}
