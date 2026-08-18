public class SubStrings{
    public static void main(String[] args){
        // .substrings() A method used to extract a portion of a string
        // .substring(start, end)

        String email = "Bromig123@gmail.com";
        String username = email.substring(0, email.indexOf('@'));
        String domain = email.substring(email.indexOf('@')+1);

        System.out.println(username);
        System.out.println(domain);

    }
}