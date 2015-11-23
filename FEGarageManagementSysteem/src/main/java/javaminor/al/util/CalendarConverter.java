package javaminor.al.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by alex on 11/22/15.
 */
@FacesConverter("javaminor.al.util.CalendarConverter")
public class CalendarConverter implements Converter {

    private static final Logger LOGGER = LogManager.getLogger(CalendarConverter.class.getName());

    // hoofdletter M *zucht*
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";
    private static final String DEFAULT_RESPONSE = "No date";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String dateString) {
        // dateString in format
        // 15-11-2015 19:12
        Calendar calendar = Calendar.getInstance();

        // add one minute to be in future,
        // will be overridden by actual value
        calendar.add(Calendar.MINUTE, 1);

        if (DEFAULT_RESPONSE.equals(dateString)) {
            return calendar;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        try {
            calendar.setTime(sdf.parse(dateString));
        } catch (ParseException e) {
            // ehm.. panic?
            LOGGER.error(e.getMessage(), e);
        }
        return calendar;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent
            uiComponent, Object o) {
        if (o == null || !(o instanceof Calendar)) {
            return DEFAULT_RESPONSE;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale
                .ENGLISH);
        return sdf.format(((Calendar) o).getTime());
    }
}
