package sprint;

import java.util.ArrayList;

public class ArrayModifier {
    public static ArrayList<Double> removeElementsBetween(ArrayList<Double> list, int index1, int index2) {
        // Return null or empty list immediately if invalid/empty
        if (list == null || list.isEmpty()) {
            return list;
        }

        // 1. Swap indices if index1 > index2
        if (index1 > index2) {
            int temp = index1;
            index1 = index2;
            index2 = temp;
        }

        // 2. Clamp indices to valid bounds [0, list.size()]
        // Note: The maximum valid boundary is list.size() because index2 is exclusive
        index1 = Math.max(0, Math.min(index1, list.size()));
        index2 = Math.max(0, Math.min(index2, list.size()));

        // 3. Remove elements in range [index1, index2) using subList.clear()
        if (index1 < index2) {
            list.subList(index1, index2).clear();
        }

        return list;
    }
}