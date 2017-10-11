package project.model.calls;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class InternationalCall extends PhoneCall {
    public InternationalCall() {
        setCallType(CallTypes.international);
    }

    @Override
    public double calculateCost() {
        return getDurationInMinutes() * 5; // cost of 1 minute
    }
}
