package project.model.calls;

import project.command.utils.ResourceBundleReader;

import java.util.ResourceBundle;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class LocalCall extends PhoneCall {
    double pricePerMinute = Double.valueOf(ResourceBundle.getBundle("phones").getString("localCallCost"));

    public LocalCall() {
        setCallType(CallTypes.local);
    }

    @Override
    public double calculateCost() {
        return getDurationInMinutes() * pricePerMinute;
    }
}
