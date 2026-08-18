package sprint;

import java.util.Arrays;

public class AnagramChecker {
    
public static boolean areAnagrams(String str1, String str2) {
        // Null checks
        if (str1 == null || str2 == null) {
            return false;
        }

        // Convert both to lower case to handle case sensitivity
        String cleaned1 = str1.toLowerCase();
        String cleaned2 = str2.toLowerCase();

        // Anagrams must have the exact same length
        if (cleaned1.length() != cleaned2.length()) {
            return false;
        }

        // Convert to character arrays and sort them
        char[] charArray1 = cleaned1.toCharArray();
        char[] charArray2 = cleaned2.toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        // Compare sorted arrays
        return Arrays.equals(charArray1, charArray2);
    }
}
