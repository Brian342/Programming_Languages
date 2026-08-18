package javaPractice;

import java.util.List;

public class PalindromeChecker {
    public static boolean isPalindrome(String text) {
        if (text == null || text.isEmpty()) {
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
    public static void main(String[] args) {
        String testString1 = "A man, a plan, a canal, Panama";
        String testString2 = "Hello, World!";
        System.out.println(PalindromeChecker.isPalindrome(testString1));
        System.out.println(PalindromeChecker.isPalindrome(testString2));
    }

}
