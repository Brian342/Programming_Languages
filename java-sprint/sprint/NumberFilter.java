package sprint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberFilter {

    private final List<Integer> numbers;

    public NumberFilter(int count, long seed) {
        this.numbers = generateRandomNumbers(count, seed);
    }

    private List<Integer> generateRandomNumbers(int count, long seed) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random(seed);
        for (int i = 0; i < count; i++) {
            // Generates numbers in range -1000 to 1000 (inclusive)
            int num = random.nextInt(2001) - 1000;
            list.add(num);
        }
        return list;
    }

    public List<Integer> getAllPrimeNumbers() {
        List<Integer> primes = new ArrayList<>();
        for (int num : numbers) {
            if (isPrime(num)) {
                primes.add(num);
            }
        }
        return primes;
    }

    public List<Integer> getDivisibleBy3ButNot5() {
        List<Integer> result = new ArrayList<>();
        for (int num : numbers) {
            if (num % 3 == 0 && num % 5 != 0) {
                result.add(num);
            }
        }
        return result;
    }

    public List<Integer> getSortedRemainingNumbers() {
        List<Integer> remaining = new ArrayList<>();
        for (int num : numbers) {
            if (num % 3 != 0 && num % 5 != 0) {
                remaining.add(num);
            }
        }
        // Sort descending
        remaining.sort((a, b) -> Integer.compare(b, a));
        return remaining;
    }

    public double computeAverageOfRemainingNumbers() {
        List<Integer> remaining = getSortedRemainingNumbers();
        if (remaining.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (int num : remaining) {
            sum += num;
        }
        return sum / remaining.size();
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
