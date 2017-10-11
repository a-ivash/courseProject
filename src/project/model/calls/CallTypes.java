package project.model.calls;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public enum CallTypes {
    local {
        @Override
        public PhoneCall getPhoneCall() {
            return new LocalCall();
        }
    },
    regional {
        @Override
        public PhoneCall getPhoneCall() {
            return new RegionalCall();
        }
    },
    international {
        @Override
        public PhoneCall getPhoneCall() {
            return new InternationalCall();
        }
    };

    public PhoneCall getPhoneCall() {
        throw new NotImplementedException();
    }

}
