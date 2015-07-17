package com.seoeun.regex;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: seoeun
 * Date: 15. 4. 12.
 * Time: 오후 3:05
 * To change this template use File | Settings | File Templates.
 */
public class RegExTest {

    @Test
    public void test() {
        RegEx regEx = new RegEx();
        Assert.assertEquals(401, regEx.extractVerion("4.0.1-SNAPSHOT"));
        Assert.assertEquals(402, regEx.extractVerion("4.0.2-SNAPSHOT"));
        Assert.assertEquals(402, regEx.extractVerion("4.0.2"));
        Assert.assertEquals(410, regEx.extractVerion("4.1.0"));
        Assert.assertEquals(420, regEx.extractVerion("4.2.0"));
        Assert.assertEquals(420, regEx.extractVerion("4.2.0-SNAPSHOT"));
        //Assert.assertEquals(420, regEx.extractVerion("4.2"));

        Assert.assertEquals(420, regEx.extractVerion2("4.2.0-SNAPSHOT"));


    }

    @Test
    public void test2() {
        RegEx regEx = new RegEx();
        Assert.assertEquals(420, regEx.extractVerion("4.2.0-SNAPSHOT"));
        Assert.assertEquals(420, regEx.extractVerion2("4.2.0-SNAPSHOT"));
        Assert.assertEquals(410, regEx.extractVerion("4.1.0"));
        Assert.assertEquals(410, regEx.extractVerion2("4.1.0"));


    }
}
