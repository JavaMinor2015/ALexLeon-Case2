package util;

import java.security.SecureRandom;

/**
 * Created by alex on 11/19/15.
 */
public final class StrUtil {
    // letter 'a'
    private static final int LEFT_LIMIT = 97;
    // letter 'z'
    private static final int RIGHT_LIMIT = 122;

    private static final SecureRandom random = new SecureRandom();

    private StrUtil() {

    }

    /**
     * Returns a random string of size specified.
     * <p>
     * http://www.baeldung.com/java-random-string
     *
     * @param size size of the string
     * @return a random string.
     */
    public static String getRandomString(final int size) {
        StringBuilder buffer = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int randomLimitedInt = LEFT_LIMIT + (int)
                    (random.nextFloat() * (RIGHT_LIMIT - LEFT_LIMIT));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
