package gui.attributes;

/**
 * Parameters for random integer generation
 */
public class RandomIntegerAttributes {

    private final int numbers;
    private final int min;
    private final int max;

    /**
     * Constructor
     *
     * @param numbers
     * @param min
     * @param max
     */
    private RandomIntegerAttributes(int numbers, int min, int max) {
        this.numbers = numbers;
        this.min = min;
        this.max = max;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public static class RandomIntegerAttributesBuilder {
        private int nestedNumbers;
        private int nestedMin;
        private int nestedMax;

        public RandomIntegerAttributesBuilder() {

        }

        public RandomIntegerAttributesBuilder numbers(int numbers) {
            this.nestedNumbers = numbers;
            return this;
        }

        public RandomIntegerAttributesBuilder min(int min) {
            this.nestedMin = min;
            return this;
        }

        public RandomIntegerAttributesBuilder max(int max) {
            this.nestedMax = max;
            return this;
        }

        public RandomIntegerAttributes build() {
            return new RandomIntegerAttributes(nestedNumbers, nestedMin, nestedMax);
        }
    }
}
