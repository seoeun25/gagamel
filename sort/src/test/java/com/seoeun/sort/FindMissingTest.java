package com.seoeun.sort;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: seoeun
 * Date: 15. 3. 29.
 * Time: 오후 2:30
 * To change this template use File | Settings | File Templates.
 */
public class FindMissingTest {

    private static FindMissing findMissing = new FindMissing();

    @Test
    public void testOneMissing() {
        Assert.assertEquals(36, findMissing.sumFromOne(8));

        int[] array = {1,5,8,3,2,4,7}; //missing 6
        Assert.assertEquals(30, findMissing.sum(array));

        int missing = findMissing.findMissingOne(array);
        Assert.assertEquals(6, missing);
    }

    @Test
    public void testTwoMissing() {
        Assert.assertEquals(1, findMissing.factorial(1));
        Assert.assertEquals(2, findMissing.factorial(2));
        Assert.assertEquals(6, findMissing.factorial(3));
        Assert.assertEquals(24, findMissing.factorial(4));



        int[] array = {1,5,8,3,2,7}; //missing 4,6
        Assert.assertEquals(1680, findMissing.multiple(array));





    }



}
