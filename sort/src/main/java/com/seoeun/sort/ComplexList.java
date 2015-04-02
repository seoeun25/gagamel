package com.seoeun.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: seoeun
 * Date: 15. 3. 29.
 * Time: 오전 1:58
 * To change this template use File | Settings | File Templates.
 */
public class ComplexList {

    Map<String, Object> map = new HashMap<String, Object>();

    List<Object> list = new ArrayList<Object>();
    List<Integer> iList = new ArrayList<Integer>();
    List<List<Integer>> listlist = new ArrayList<List<Integer>>();

    public ComplexList() {
        list.add(1);
        Integer[] list1 = {new Integer(3), new Integer(4), new Integer(5)};
        list.add(2);
        Integer[] list2 = {new Integer(8), new Integer(4), new Integer(6)};

        iList.add(1);
        iList.add(2);
        listlist.add(Arrays.asList(list1));
        listlist.add(Arrays.asList(list2));
    }

    public void add (int a) {
        list.add(new Integer(a));
    }

    public void add(List<Integer> list) {
        this.list.add(list);
    }

    public void sort() {

    }

    public void get(int index) {
        iList.get(index);
    }

    public int indexOf(int value) {
        return 0;
    }

}
