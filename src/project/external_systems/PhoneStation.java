package project.external_systems;

import project.model.calls.CallTypes;
import project.model.calls.PhoneCall;
import project.model.calls.decorators.NightCall;
import project.model.calls.decorators.WeekendCall;
import project.model.users.Phone;

import java.util.*;

public class PhoneStation implements PhoneStationObservable, Runnable {
    PhoneSystemObserver observer;
    List<Phone> phones;
    Random random = new Random();

    @Override
    public void addPhoneSystemObserver(PhoneSystemObserver observer) {
        this.observer = observer;
        phones = observer.getActivePhones();
    }

    @Override
    public void run(){
        while (true) {
            try {
                int pause = 100000 + random.nextInt(100000);
                Thread.sleep(pause);
                PhoneCall phoneCall = createPhoneCall();
                observer.notifyForPhoneCall(phoneCall);
            } catch (InterruptedException e) {

            }
        }
    }

    private PhoneCall createPhoneCall() {
        int callTypeIndex = random.nextInt(CallTypes.values().length);
        PhoneCall phoneCall = CallTypes.values()[callTypeIndex].getPhoneCall();

        int phoneIndex = random.nextInt(phones.size());
        phoneCall.setPhone(phones.get(phoneIndex));

        phoneCall.setDurationInSeconds(random.nextInt(10000));
        phoneCall.setCallDate(new Date());
        phoneCall = detectCallType(phoneCall);
        double cost = phoneCall.calculateCost();
        phoneCall.setCost(cost);
        return phoneCall;
    }

    /**
     * Detecting phone call type based on it's call datetime
     * */
    private PhoneCall detectCallType(PhoneCall phoneCall) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(phoneCall.getCallDate());
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            phoneCall = new WeekendCall(phoneCall);
        }

        if (calendar.get(Calendar.HOUR_OF_DAY) >= 22 || calendar.get(Calendar.HOUR_OF_DAY) < 6) {
            phoneCall = new NightCall(phoneCall);
        }

        return phoneCall;
    }
}
