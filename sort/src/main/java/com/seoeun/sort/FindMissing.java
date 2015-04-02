package com.seoeun.sort;

/**
 *
 */
public class FindMissing {

    /**
     * Sum from 1 to <tt>number</tt>
     * @param number
     * @return
     */
    public int sumFromOne(int number) {
        // 1 + 2 + 3 + 4 + number

        int sum = number * (number +1) /2;

        return sum;
    }

    public int sum(int[] array) {
        int sum = 0;
        for (int i=0; i<array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    public int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n-1);
        }
    }

    public int multiple(int[] array) {
        int multiple = 1;

        for (int i=0; i<array.length; i++) {
            if (array[i] !=0) {
                multiple = multiple * array[i];
            }
        }
        return multiple;
    }

    /**
     * Find one missing number into <tt>array</tt>.
     * @param array distinct int
     * @return
     */
    public int findMissingOne(int[] array) {

        int n = array.length + 1;

        int expectedSum =sumFromOne(n);
        int realSum = sum(array);

        int missing = expectedSum - realSum;

        return missing;
    }

    public int[] findMissingTwo(int[] array) {
        int x = 0;
        int y = 0;
        int n = array.length + 2;

        int expectedFactorial = factorial(n);
        int multiple = multiple(array);

        int a = expectedFactorial - multiple; // x * y
        int b = sumFromOne(n) - sum(array); // x + y


        return null;
    }
}
