package sprint;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomIterator implements Iterator<Integer> {

    private final List<Integer> numbers;
    private int currentIndex;

    public CustomIterator(List<Integer> numbers) {
        this.numbers = numbers;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return numbers != null && currentIndex < numbers.size();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements available in the list.");
        }
        return numbers.get(currentIndex++);
    }
}
