package sprint;


public class ArraySorter {
    public double[] sortArray(double[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        int n = arr.length;
        
        // Pass through the array to compare adjacent elements
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            
            for (int j = 0; j < n - 1 - i; j++) {
                // If the current element is greater than the next element, swap them
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            // Optimization: Stop early if no elements were swapped in this pass
            if (!swapped) {
                break;
            }
        }

        return arr;
    }
}