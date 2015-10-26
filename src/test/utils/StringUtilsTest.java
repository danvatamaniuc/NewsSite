package test.utils;

import junit.framework.TestCase;
import main.utils.StringUtils;

/**
 * Created by 1 on 10/9/2015.
 */
public class StringUtilsTest extends TestCase {

    public void testCheckForNumbers() throws Exception {
        assertEquals(true, StringUtils.checkForNumbers("a52"));
        assertEquals(true, StringUtils.checkForNumbers("12a"));
        assertEquals(false, StringUtils.checkForNumbers("aa"));
    }
}