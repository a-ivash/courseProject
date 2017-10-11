package project.model.calls;

import java.util.ResourceBundle;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class RegionalCall extends PhoneCall {
    double pricePerMinute = Double.valueOf(ResourceBundle.getBundle("phones").getString("regionalCallCost"));

    public RegionalCall() {
        setCallType(CallTypes.regional);
    }

    @Override
    public double calculateCost() {
        return getDurationInMinutes() * pricePerMinute;
    }
}
