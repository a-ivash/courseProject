package project.model.calls.decorators;

import project.model.calls.PhoneCall;

import java.util.ResourceBundle;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class NightCall extends CallDecorator {
    double callRatio = Double.valueOf(ResourceBundle.getBundle("phones").getString("nightCallRatio"));

    public NightCall(PhoneCall phoneCall) {
        super(phoneCall);
    }

    @Override
    public double calculateCost() {
        return callRatio * phoneCall.getCost();
    }
}
