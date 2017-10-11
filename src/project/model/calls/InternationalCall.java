package project.model.calls;

import java.util.ResourceBundle;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class InternationalCall extends PhoneCall {
    double pricePerMinute = Double.valueOf(ResourceBundle.getBundle("phones").getString("internationalCallCost"));
    public InternationalCall() {
        setCallType(CallTypes.international);
    }

    @Override
    public double calculateCost() {
        return getDurationInMinutes() * pricePerMinute;
    }
}
