package sprint;

import java.util.List;
import java.util.stream.Collectors;

public class StreamPolice {

    public List<Integer> processNumbers(List<Integer> numbers) {
        if (numbers == null) {
            return List.of();
        }

        return numbers.stream()
                // Filter out negative numbers
                .filter(n -> n >= 0)
                // Filter out numbers divisible by 5 but not divisible by 10
                .filter(n -> !(n % 5 == 0 && n % 10 != 0))
                .collect(Collectors.toList());
    }
}
