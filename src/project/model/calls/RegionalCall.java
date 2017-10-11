package project.model.calls;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class RegionalCall extends PhoneCall {

    public RegionalCall() {
        setCallType(CallTypes.regional);
    }

    @Override
    public double calculateCost() {
        return getDurationInMinutes() * 2.5; // cost of 1 minute
    }
}
