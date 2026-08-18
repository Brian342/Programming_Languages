package sprint;

import java.util.List;
import java.util.stream.Collectors;

public class StringToIntConverter {

    public List<Integer> convertStringListToIntList(List<String> list) {
        if (list == null) {
            return List.of();
        }

        return list.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
