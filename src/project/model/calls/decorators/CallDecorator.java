package project.model.calls.decorators;

import project.model.calls.CallTypes;
import project.model.calls.PhoneCall;
import project.model.orders.Payment;
import project.model.users.Phone;

import java.util.Date;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public abstract class CallDecorator extends PhoneCall {
    protected PhoneCall phoneCall;

    public CallDecorator(PhoneCall phoneCall) {
        this.phoneCall = phoneCall;
    }

    @Override
    public Payment getPayment() {
        return phoneCall.getPayment();
    }

    @Override
    public void setPayment(Payment payment) {
        phoneCall.setPayment(payment);
    }

    @Override
    public void setId(Long id) {
        phoneCall.setId(id);
    }

    @Override
    public Long getId() {
        return phoneCall.getId();
    }

    @Override
    public Phone getPhone() {
        return phoneCall.getPhone();
    }

    @Override
    public void setPhone(Phone phone) {
        phoneCall.setPhone(phone);
    }

    @Override
    public long getDurationInSeconds() {
        return phoneCall.getDurationInSeconds();
    }

    @Override
    public void setDurationInSeconds(long durationInSeconds) {
        phoneCall.setDurationInSeconds(durationInSeconds);
    }

    @Override
    public long getDurationInMinutes() {
        return phoneCall.getDurationInMinutes();
    }

    @Override
    public Date getCallDate() {
        return phoneCall.getCallDate();
    }

    @Override
    public void setCallDate(Date callDate) {
        phoneCall.setCallDate(callDate);
    }

    @Override
    public double getCost() {
        return phoneCall.getCost();
    }

    @Override
    public void setCost(double cost) {
        phoneCall.setCost(cost);
    }

    @Override
    public CallTypes getCallType() {
        return phoneCall.getCallType();
    }

    @Override
    public void setCallType(CallTypes callType) {
        phoneCall.setCallType(callType);
    }

    public abstract double calculateCost();
}
