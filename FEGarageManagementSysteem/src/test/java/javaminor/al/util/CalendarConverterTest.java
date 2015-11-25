package javaminor.al.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javaminor.al.domain.beans.MockFacesContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 11/23/15.
 */
public class CalendarConverterTest {
    private CalendarConverter calendarConverter;
    private Calendar testCalendar;
    private String testString;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        calendarConverter = new CalendarConverter();
        testCalendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH);
        testString = sdf.format(testCalendar.getTime());
    }

    @Test
    public void testGetAsObject() throws Exception {
        //"dd-MM-yyyy HH:mm"
        Calendar result = (Calendar) calendarConverter.getAsObject(MockFacesContext.mockFacesContext(), null, testString);
        assertThat(result.get(Calendar.DAY_OF_MONTH), is(testCalendar.get(Calendar.DAY_OF_MONTH)));
        assertThat(result.get(Calendar.DAY_OF_YEAR), is(testCalendar.get(Calendar.DAY_OF_YEAR)));

        result = (Calendar) calendarConverter.getAsObject(MockFacesContext.mockFacesContext(), null, "No date");
        assertThat(result.get(Calendar.MINUTE), is(testCalendar.get(Calendar.MINUTE) + 5));

        result = (Calendar) calendarConverter.getAsObject(MockFacesContext.mockFacesContext(), null, "Oh my god this is not a date at all");
        assertThat(result.get(Calendar.MINUTE), is(testCalendar.get(Calendar.MINUTE) + 5));
    }

    @Test
    public void testGetAsString() throws Exception {
        String result = calendarConverter.getAsString(MockFacesContext.mockFacesContext(), null, testCalendar);
        assertThat(result, is(testString));

        result = calendarConverter.getAsString(MockFacesContext.mockFacesContext(), null, null);
        assertThat(result, is("No date"));

        result = calendarConverter.getAsString(MockFacesContext.mockFacesContext(), null, null);
        assertThat(result, is("No date"));
    }
}