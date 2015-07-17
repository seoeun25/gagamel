package com.seoeun.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: seoeun
 * Date: 15. 3. 29.
 * Time: 오전 3:20
 * To change this template use File | Settings | File Templates.
 */
public class ComplexListTest {

    @Test
    public void test(){

        List<Integer> a = new ArrayList<Integer>();
        a.add(new Integer(1));
        a.add(new Integer(3));
        a.add(new Integer(5));

        List<Integer> b = new ArrayList<Integer>();
        b.add(new Integer(1));
        b.add(new Integer(3));
        b.add(new Integer(5));

        List<Integer> c = new ArrayList<Integer>();
        c.add(new Integer(3));
        c.add(new Integer(1));
        c.add(new Integer(5));

        System.out.println("equal a,b: " + a.equals(b));
        System.out.println("equal a,c: " + a.equals(c));

        Map<List<Integer>, List<Integer>> map =  new HashMap<List<Integer>, List<Integer>>();
        map.put(a,a);
        map.put(b,b);
        map.put(c,c);
        System.out.println(" map size : " + map.size());

        List<Integer> d = new ArrayList<Integer>();
        d.add(3);
        d.add(2);
        d.add(4);
        d.add(3);

        System.out.println("index of 3 = " + d.indexOf(3));





    }
}
