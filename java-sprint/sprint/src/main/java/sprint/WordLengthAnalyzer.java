package sprint;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordLengthAnalyzer {

    public Map<Integer, Integer> analyzeWordLengths(List<String> words) {
        if (words == null) {
            return Collections.emptyMap();
        }

        return words.stream()
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                Long::intValue
                        )
                ));
    }
}
