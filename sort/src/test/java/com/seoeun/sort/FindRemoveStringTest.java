package com.seoeun.sort;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author seoeun
 * @since ${VERSION} on 2/17/17
 */
public class FindRemoveStringTest {

    @Test
    public void findAndRemoveTest1() {

        FindRemoveString findRemoveString = new FindRemoveString();

        String org = "abcdefg-abcd-hggg-agcdd-ssab";
        String str = "abc";

        Assert.assertEquals("defg-d-hggg-agcdd-ssab", findRemoveString.findAndRemove(org, str));
    }

    @Test
    public void findAndReplaceTest1() {

        FindRemoveString findRemoveString = new FindRemoveString();

        String org = "helloabc-azraelabcg-abcd-hggg";
        String str = "abc";
        String replacement = "xyz";

        Assert.assertEquals("helloxyz-azraelxyzg-xyzd-hggg", findRemoveString.findAndReplace(org, str, replacement));
    }


}
