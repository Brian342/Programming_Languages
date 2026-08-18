package sprint;

import java.util.List;
import java.util.Optional;

public class NumberProcessor {

    public Optional<Integer> processNumbers(List<Integer> numbers) {
        if (numbers == null) {
            return Optional.empty();
        }

        return numbers.stream()
                // Keep only integers greater than or equal to 10
                .filter(n -> n >= 10)
                // Calculate the product using reduce with the binary multiplication operator
                .reduce((a, b) -> a * b);
    }
}
