package com.seoeun.homework.app;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PalindromeAppTest {

    public static PalindromeApp app;

    @BeforeClass
    public static void setupBeforeClass() {
        app = new PalindromeApp();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        app = null;
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsPalindrome() {
        testIsPalindrome(124421);
        testIsPalindrome(3467643);
        testIsPalindrome(1245421);
    }

    @Test
    public void testIsPalindromeNegative() {
        testIsPalindromeNegative(12320);
        testIsPalindromeNegative(124521);
    }

    @Test
    public void testGetHigerPalindrome() {
        Assert.assertEquals(12321, app.getHigherPalindrome(12320));
        Assert.assertEquals(12421, app.getHigherPalindrome(12340));
        Assert.assertEquals(12321, app.getHigherPalindrome(12310));
        Assert.assertEquals(13331, app.getHigherPalindrome(12325));

        Assert.assertEquals(133331, app.getHigherPalindrome(123324));
        Assert.assertEquals(124421, app.getHigherPalindrome(123424));
        Assert.assertEquals(125521, app.getHigherPalindrome(125424));

        Assert.assertEquals(1246421, app.getHigherPalindrome(1245421));
        Assert.assertEquals(12466421, app.getHigherPalindrome(12455421));
    }

    @Test
    public void testToChar() {
        Assert.assertEquals('1', app.toChar(1));
        Assert.assertEquals('5', app.toChar(5));
        Assert.assertEquals('9', app.toChar(9));
    }

    private void testIsPalindrome(int a) {
        Assert.assertTrue(app.isPalindrome(String.valueOf(a)));
    }

    private void testIsPalindromeNegative(int a) {
        Assert.assertFalse(app.isPalindrome(String.valueOf(a)));
    }
}
