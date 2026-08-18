package javaPractice;

public class OccurrenceCounter {
    public static int countOccurrences(int[] arr, int element, int index){
        if(arr == null || index < 0 || index >= arr.length){
            return 0;
        }
        int match = (arr[index] == element) ? 1 : 0;
        return match + countOccurrences(arr, element, index + 1);
    }
    public static void main(String[] args) {
        OccurrenceCounter counter = new OccurrenceCounter();
        int[] arr = {1, 2, 3, 2, 4, 2, 5};
        System.out.println(counter.countOccurrences(arr, 2, 0));
        System.out.println(counter.countOccurrences(arr, 6, 0));
        System.out.println(counter.countOccurrences(null, 1, 0));
        System.out.println(counter.countOccurrences(new int[]{}, 1, 0));
    }
}
