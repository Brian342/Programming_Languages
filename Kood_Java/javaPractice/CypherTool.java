package javaPractice;

import java.util.Scanner;

public class CypherTool {
    private static void checkForExit(String input){
        if(input.equalsIgnoreCase("exit") || input.equals("0")){
            System.out.println("Existing the System\nGoodbye!");
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        while (true) {
            InputData input = getInput();

            // encryption code
            String res = ""; // placeholder for the message string

            if (input.operation == 1) {
                res = switch (input.choice) {
                    case 1 -> encryptRot13(input.message);
                    case 2 -> encryptAtbash(input.message);
                    case 3 -> encryptCaesar(input.message);
                    default -> "Invalid Choice of encyption";
                };
// decryption of the message
            } else if (input.operation == 2) {
                res = switch (input.choice) {
                    case 1 -> decryptRot13(input.message);
                    case 2 -> decryptAtbash(input.message);
                    case 3 -> decryptCaesar(input.message);
                    default -> "Invalid choice of decyption";
                };
            }

            System.out.println(res);
        }
    }

    public static InputData getInput() {
        Scanner scanner = new Scanner(System.in);
        int operation = 0;
        int choice = 0;
        System.out.println("Welcome to the Cypher Tool! ");

        while (true) {
            System.out.print("""
                    Select operation:
                    1. Encrypt
                    2. Decrypt
                    :>\s""");
            String exitInput = scanner.next();
            checkForExit(exitInput);

            try {
                operation = Integer.parseInt(exitInput);
                if (operation == 1 || operation == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice! Please select 1 or 2.\n");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please Enter a valid number!\n");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("""
                    Select Cypher:
                    1. ROT13
                    2. Atbash
                    3. Caesar cypher
                    :>\s""");

            String exitInput = scanner.next();
            checkForExit(exitInput);

            try{
                choice = Integer.parseInt(exitInput);
                if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
                    break;
                } else {
                    System.out.println("Invalid choice! Please select 1, 2, 3 or 4.\n");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please Enter a valid number!\n");
                scanner.next();
            }
        }


        scanner.nextLine();

    System.out.print("Enter your message: ");
    String message = scanner.nextLine();
    System.out.println(message);


        return new InputData(operation, choice, message);
    }
    public static String encryptRot13(String s){
        if( s==null||s.isEmpty()){
            System.exit(0);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            // handle all the uppercase letters
            if(c >= 'A' && c<= 'Z'){
                if(c <= 'M'){
                    c += 13;
                }else{
                    c -= 13;
                }
            }

            // Handling all the Lowercase letters
            if(c >= 'a' && c<= 'z'){
                if(c <= 'm'){
                    c += 13;
                }else{
                    c -= 13;
                }
            }
            sb.append(c);
        }

        System.out.println();
        System.out.println("Message after encrypted");
        return sb.toString();
    }
    public static String encryptAtbash(String s){

        if(s == null || s.isEmpty()){
            System.exit(0);
        }
        StringBuilder sb = new StringBuilder();

        // handling upper case
        for(char ch: s.toCharArray()){
            if(ch >= 'A' && ch <= 'Z'){
                char encrypted = (char) ('Z' - (ch - 'A'));
                sb.append(encrypted);

                //Handling lower case
            }else if(ch >= 'a' && ch <= 'z'){
                char encrypted = (char) ('z' - (ch - 'a'));
                sb.append(encrypted);

            }else{
                sb.append(ch);
            }

        }
        System.out.println();
        System.out.println("Message after encrypted");
        return sb.toString();
    }

    public static String caesarCipher(String message, int shift) {
        StringBuilder result = new StringBuilder();

        //Check for null
        if (message == null || message.isEmpty()) {
            return "";
        }

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);

            if (Character.isUpperCase(c)) {
                char encrypted = (char) (((c - 'A' + shift + 26) % 26) + 'A');
                result.append(encrypted);

            } else if (Character.isLowerCase(c)) {
                char encrypted = (char) (((c - 'a' + shift + 26) % 26) + 'a');
                result.append(encrypted);

            } else {
                // Leave numbers, spaces and punctuation unchanged
                result.append(c);
            }
        }

        return result.toString();

    }

    public static String decryptRot13(String s){

        System.out.println();
        System.out.println("Message after Decrypted");
        return encryptRot13(s);
    }
    public static String decryptAtbash(String s){

        if(s == null || s.isEmpty()){
            System.exit(0);
        }
        StringBuilder sb = new StringBuilder();

        // handling upper case
        for(char ch: s.toCharArray()){
            if(ch >= 'A' && ch <= 'Z'){
                sb.append((char) ('Z' - ch + 'A'));

                //Handling lower case
            }else if(ch >= 'a' && ch <= 'z'){
                sb.append((char) ('z' - ch + 'a'));

            }else{
                sb.append(ch);
            }

        }

        System.out.println();
        System.out.println("Message after Decrypted");
        return sb.toString();
    }
    // Encrypts the message using a Caesar Cipher with a shift of 3
    public static String encryptCaesar(String message) {
        return caesarCipher(message,3);
    }
    // Decrypts the message using a Caesar Cipher with a shift of -3
    public static String decryptCaesar(String message) {
        return caesarCipher(message, -3);
    }
}

