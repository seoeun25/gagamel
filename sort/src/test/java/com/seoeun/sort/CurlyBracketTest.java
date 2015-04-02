package com.seoeun.sort;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: seoeun
 * Date: 15. 3. 29.
 * Time: 오전 1:46
 * To change this template use File | Settings | File Templates.
 */
public class CurlyBracketTest {

    private static CurlyBracket curlyBracket;

    @BeforeClass
    public static void setup() {
        curlyBracket = new CurlyBracket();

    }

    @Test
    public void testIsClosed() {
        String a = "hello {azrael} and {hi}";
        String b = "hello {azrael} and {hi";
        String c = "hello {azrael} and hi}";

        Assert.assertEquals(0, curlyBracket.getStatus(a));
        Assert.assertEquals(1, curlyBracket.getStatus(b));
        Assert.assertEquals(-1, curlyBracket.getStatus(c));


        Assert.assertEquals(true, curlyBracket.isClosed(a));
        Assert.assertEquals(false, curlyBracket.isClosed(b));
        Assert.assertEquals(false, curlyBracket.isClosed(c));
    }
}
