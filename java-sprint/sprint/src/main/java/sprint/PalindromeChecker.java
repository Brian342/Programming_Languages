package sprint;

public class PalindromeChecker {
     public static boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }

        String Cleaned = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left = 0;
        int right = Cleaned.length() - 1;

        while (left < right) {
            if (Cleaned.charAt(left) != Cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    
    }
}
