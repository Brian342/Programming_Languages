package sprint;

import java.util.List;
import java.util.stream.Collectors;

public class EmailDomainExtractor {

    public List<String> extractDomains(List<String> emails) {
        if (emails == null) {
            return List.of();
        }

        return emails.stream()
                // Filter out invalid email addresses: must have exactly one '@', with text before and after it
                .filter(email -> email != null 
                        && email.indexOf('@') > 0 
                        && email.indexOf('@') == email.lastIndexOf('@') 
                        && email.indexOf('@') < email.length() - 1)
                // Extract domain and convert to lowercase
                .map(email -> email.substring(email.indexOf('@') + 1).toLowerCase())
                // Remove duplicate domains
                .distinct()
                .collect(Collectors.toList());
    }
}
