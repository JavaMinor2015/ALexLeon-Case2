package javaminor.al.domain.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javaminor.al.entities.concrete.Car;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Leon Stam on 25-11-2015.
 */
@Named("carView")
@SessionScoped
@Getter
@Setter
public class CarViewManagedBean implements Serializable {
    private static final long serialVersionUID = 1756969611443591160L;

    private Car car;

    private DateFormat format;

    /**
     * I'm a constructor, call me.
     */
    public CarViewManagedBean() {
        format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    }

    /**
     * Set the car for the info page.
     *
     * @param car The car
     * @return the page to go to
     */
    public String toCar(final Car car) {
        this.car = car;
        return "viewCarInfo.xhtml";
    }

    /**
     * Transform a Calender date into a presentable string date.
     *
     * @param calendar The calender
     * @return the string
     */
    public String toPresentableDate(final Calendar calendar) {
        return format.format(calendar.getTime());
    }

}
