package sprint;


import java.util.List;

public class ListManipulator {

    public List<String> manipulateList(List<String> list) {
        // Handle null or empty list gracefully
        if (list == null || list.isEmpty()) {
            return list;
        }

        // 1. Remove the last element from the list
        list.remove(list.size() - 1);

        // Check again after removal in case the initial list only had 1 item
        if (!list.isEmpty()) {
            // 2. Set the new last element to: "The size of the list is " + size of the list
            int lastIndex = list.size() - 1;
            list.set(lastIndex, "The size of the list is " + list.size());
        }

        // 3. Add the string "last" to the end of the list
        list.add("last");

        // 4. Set the first element of the list to the string "first"
        list.set(0, "first");

        // 5. Return the manipulated list
        return list;
    }
}
