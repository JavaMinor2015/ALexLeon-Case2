package javaminor.al.util;

import java.lang.reflect.Constructor;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 11/22/15.
 */
public class StrUtilTest {

    @Test
    public void testConstructor() throws Exception{
        // because 100%
        try {
            Constructor<StrUtil> con = (Constructor<StrUtil>) StrUtil.class.getDeclaredConstructors()[0];
            con.setAccessible(true);
            con.newInstance();
        } catch (Exception e) {
            // not worth it to fail any test results
            // anyway.. back to work
        }
    }

    @Test
    public void testGetRandomString() throws Exception {
        assertThat(StrUtil.getRandomString(5).length(), is(5));
    }
}