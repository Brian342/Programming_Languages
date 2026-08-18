//package javaPractice;
//
//public class ParenthesesBalanceChecker {
//
//    public boolean isBalanced(String str) {
//        if (str == null) {
//            return false;
//        }
//        return (str, 0, 0);
//    }
//
//    private boolean checkBalance(String str, int index, int balance) {
//        if (balance < 0) {
//            return false;
//        }
//
//        if (index == str.length()) {
//            return balance == 0;
//        }
//        char ch = str.charAt(index);
//
//        if (ch == '(') {
//            return checkBalance(str, index + 1, balance + 1);
//        } else if (ch == ')') {
//            return checkBalance(str, index + 1, balance - 1);
//        } else {
//            return checkBalance(str, index + 1, balance);
//        }
//    }
//
//    public static void main(String[] args) {
//        ParenthesesBalanceChecker checker = new ParenthesesBalanceChecker();
//        System.out.println(checker.isBalanced("(())"));
//        System.out.println(checker.isBalanced("(a()b()c)"));
//        System.out.println(checker.isBalanced("(()"));
//        System.out.println(checker.isBalanced(")("));
//        System.out.println(checker.isBalanced(""));
//        System.out.println(checker.isBalanced(null));
//    }
//}
//
