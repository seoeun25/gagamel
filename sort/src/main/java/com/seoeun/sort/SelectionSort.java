package com.seoeun.sort;

/**
 * array에서 가장 작은 값과 맨 앞이 값을 바꾼다.
 * 맨 앞의 원소를 뺀 나머지 array에서 가장 작은 값을 고르고, 그 값과 맨 값을 바꾼다.
 * O(n^2)
 */
public class SelectionSort {

    private int[] array;
    public int[] sort(int[] array) {
        this.array = array;

        int minIndex = 0;
        for (int i=0; i<array.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                    swap(minIndex, i);
                }
            }
        }

        return array;
    }

    private void swap(int aIndex, int bIndex) {
        int tmp = array[aIndex];
        array[aIndex] = array[bIndex];
        array[bIndex] = tmp;
    }

    private void printArray() {

    }
}
