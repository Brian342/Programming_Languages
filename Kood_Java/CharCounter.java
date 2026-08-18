import java.util.Scanner;
public class CharCounter {
    /*
    * Implement a class CharCounter with a method countOccurrences(String input, char target)
    * that returns the number of times a specific letter appears
    * in the provided string.

    Examples
    countOccurrences("Banana", 'a') should return 3
    countOccurrences("Hello WORLD", 'o') should return 2
    * Consider how you might handle case insensitivity.
    Think about how to efficiently iterate through the string without unnecessary operations.
*/

        public static int countOccurrences(String input, char target) { // case-insensitive
            if(input == null || input.isEmpty()){
                return 0;
            }

            // Convert the entire string to lower case
            String lowerInput = input.toLowerCase();

            // Convert the target character to lower case
            char lowerTarget = Character.toLowerCase(target);

            int count = 0;
            for(int i =0; i < lowerInput.length(); i++){
                if(lowerInput.charAt(i) == lowerTarget){
                    count++;
                }
            }
            return count;

    }
    public static void main(String[] args){
            // creating a user input object
         Scanner scanner = new Scanner(System.in);
         String text;
         int res;
         char value;

         // Creating an object of the Calculator class
//        CharCounter charcount = new CharCounter();

        System.out.print("Enter your String: ");
        text = scanner.nextLine();

        System.out.print("Enter character to look for: ");
        value =  scanner.nextLine().charAt(0);

        res = CharCounter.countOccurrences(text, value);

        System.out.print("The character is: "+ res);
    }
}
