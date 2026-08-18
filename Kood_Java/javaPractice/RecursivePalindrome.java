package javaPractice;

public class RecursivePalindrome {
    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        return isPalindromeHelper(cleaned, 0, cleaned.length() - 1);
    }

    private boolean isPalindromeHelper(String str, int start, int end) {
        if (start >= end) {
            return true;
        }

        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }
        return isPalindromeHelper(str, start + 1, end - 1);
    }

    public static void main(String[] args) {
        RecursivePalindrome checker = new RecursivePalindrome();
        System.out.println(checker.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(checker.isPalindrome("race a car"));
        System.out.println(checker.isPalindrome(""));
        System.out.println(checker.isPalindrome("a"));
        System.out.println(checker.isPalindrome("   "));
        System.out.println(checker.isPalindrome(null));
    }
}

