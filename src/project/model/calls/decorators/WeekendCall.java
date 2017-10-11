package project.model.calls.decorators;

import project.model.calls.PhoneCall;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class WeekendCall extends CallDecorator {
    public WeekendCall(PhoneCall phoneCall) {
        super(phoneCall);
    }

    @Override
    public double calculateCost() {
        return 0.9 * getCost();
    }
}
