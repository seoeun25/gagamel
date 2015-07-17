package com.seoeun.sort;

/**
 * 정렬된 array 에서 k 값을 가진 index 찾기
 */
public class BinarySearch {

    public int search(int[] array, int value) {

        int low = 0;
        int high = array.length -1;

        while (low <= high) {
            int midIndex = (low + high) /2;
            System.out.println("low : " + low + ", high : " + high + ", mid : " + midIndex);
            if (array[midIndex] < value) {
                low = midIndex + 1;
            } else if (array[midIndex] > value) {
                high = midIndex -1;
            } else if (array[midIndex] == value){
                return midIndex;
            }
        }
        return -1;
    }

    public int searchCount(int[] array, int value) {

        int low = 0;
        int high = array.length -1;

        int count = 0;

        while (low <= high) {
            int midIndex = (low + high) /2;
            System.out.println("low : " + low + ", high : " + high + ", mid : " + midIndex);
            if (array[midIndex] < value) {
                low = midIndex + 1;
            } else if (array[midIndex] > value) {
                high = midIndex -1;
            } else if (array[midIndex] == value){
                count++;
                int n =1;
                while(array[midIndex + n] == value) {
                    count++;
                    n++;
                    System.out.println(" n : " + n);
                }
                n = 1;
                while (array[midIndex-n] == value) {
                    count++;
                    n++;
                    System.out.println(" nn : " + n);
                }
                return count;
            }
        }
        return count;
    }



}
