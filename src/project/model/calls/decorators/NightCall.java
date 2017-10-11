package project.model.calls.decorators;

import project.model.calls.PhoneCall;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class NightCall extends CallDecorator {

    public NightCall(PhoneCall phoneCall) {
        super(phoneCall);
    }

    @Override
    public double calculateCost() {
        return 0.5 * phoneCall.getCost();
    }
}
