package business;

/**
 * Created by alex on 11/19/15.
 */
public final class Rules {


    /**
     * Size constraints.
     */
    public static final int FIRST_NAME_MIN_LENGTH = 2;
    public static final int FIRST_NAME_MAX_LENGTH = 50;
    public static final int INSERTION_MIN_LENGTH = 0;
    public static final int INSERTION_MAX_LENGTH = 20;
    public static final int LAST_NAME_MIN_LENGTH = 2;
    public static final int LAST_NAME_MAX_LENGTH = 100;
    public static final int STREET_NAME_MIN_LENGTH = 2;
    public static final int STREET_NAME_MAX_LENGTH = 100;
    public static final int STREET_NUMBER_MIN_LENGTH = 1;
    public static final int STREET_NUMBER_MAX_LENGTH = 6;
    public static final int CITY_MIN_LENGTH = 2;
    public static final int CITY_MAX_LENGTH = 100;
    public static final int PHONE_NUMBER_MIN_LENGTH = 10;
    public static final int PHONE_NUMBER_MAX_LENGTH = 15;
    public static final int COMPANY_NAME_MIN_LENGTH = 1;
    public static final int COMPANY_NAME_MAX_LENGTH = 100;

    /**
     * Regular Expressions.
     */
    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Rules() {
    }
}
