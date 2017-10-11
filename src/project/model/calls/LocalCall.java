package project.model.calls;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class LocalCall extends PhoneCall {

    public LocalCall() {
        setCallType(CallTypes.local);
    }

    @Override
    public double calculateCost() {
        return getDurationInMinutes() * 1.5; // cost of 1 minute
    }
}
