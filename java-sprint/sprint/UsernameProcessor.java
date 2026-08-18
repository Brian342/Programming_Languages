package sprint;

import java.util.List;

public class UsernameProcessor {

    public String findFirstUsername(List<String> usernames) {
        if (usernames == null) {
            return "Anonymous";
        }

        return usernames.stream()
                .findFirst()
                .orElse("Anonymous");
    }
}
