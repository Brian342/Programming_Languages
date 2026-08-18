package sprint;

public class OccurrenceCounter {
    public static int countOccurrences(int[] arr, int element, int index){
        if(arr == null || index < 0 || index >= arr.length){
            return 0;
        }
        int match = (arr[index] == element) ? 1 : 0;
        return match + countOccurrences(arr, element, index + 1);
    }
    
}
