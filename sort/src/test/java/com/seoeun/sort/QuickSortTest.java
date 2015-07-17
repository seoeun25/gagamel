package com.seoeun.sort;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class QuickSortTest {

    public static HashMap<Object, Object> map = new HashMap<Object, Object>();

    @Test
    public void test(){
        Object obj = new Object();
        System.out.println(" hash  : " + obj.hashCode());
        int hash = obj.hashCode();

        HashObject obj1 = new HashObject(15);
        HashObject obj2 = new HashObject(2);
        HashObject obj3 = new HashObject(34);
        HashObject obj4 = new HashObject(66);

        indexFor(obj1.hashCode(), 16);
        map.put(obj1,"obj1");
        printHashMap();
        indexFor(obj2.hashCode(), 16);
        map.put(obj2, "obj2");
        printHashMap();

        indexFor(obj3.hashCode(), 16);
        map.put(obj3,"obj3");
        printHashMap();

        indexFor(obj4.hashCode(), 16);
        map.put(obj4,"obj4");
        printHashMap();


        //int index = indexFor(hash, 3);



    }

    private static class HashObject {

        int id;
        HashObject(int id) {
            this.id = id;

        }
        public int hashCode(){
            return id;
        }

    }

    private void printHashMap(){
        for (Map.Entry<Object,Object> entry : map.entrySet()) {
            System.out.println(entry.getKey().hashCode());
        }
    }

    private int indexFor(int h, int length) {
        int index =  h & (length -1);
        System.out.println("h = " + h + ", index : " + index);
        return index;
    }

    @Test
    public void testSort() {
        int[] array = {7, 3, 5, 8, 2, 4, 10};

        QuickSort quickSort = new QuickSort();
        int[] a = new int[]{3, 1, 8, 6, 5, 7};
        printArray(a);

        // sort the array
        quickSort.sort(a);

        System.out.println("-----");

        //prints the sorted array
        quickSort.printArray();

        //---
        SelectionSort selectionSort = new SelectionSort();
        int[] result = selectionSort.sort(a);
        System.out.println("---- selectionSort");
        printArray(result);
    }

    @Test
    public void testBinarySearch() {
        int[] a = new int[]{1, 2, 4, 8, 9, 11};
        printArray(a);
        // search 5
        BinarySearch search = new BinarySearch();
        Assert.assertEquals(-1, search.search(a, 5));
        Assert.assertEquals(3, search.search(a,8));
        Assert.assertEquals(1, search.search(a,2));

        int[] b = new int[]{1, 2, 4, 5, 6, 6, 6,8, 9, 11};
        printArray(b);
        Assert.assertEquals(3, search.searchCount(b, 6));
        int[] c = new int[]{1, 2, 7, 7,8, 9, 11};
        printArray(c);
        Assert.assertEquals(2, search.searchCount(c, 7));
    }

    public void printArray(int[] a){
        for(int i : a){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
