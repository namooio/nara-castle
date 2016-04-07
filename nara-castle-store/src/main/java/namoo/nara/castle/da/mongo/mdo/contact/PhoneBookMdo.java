package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.UserPhone;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookMdo {
    //
    private List<UserPhone> phoneList = new ArrayList<>();

    public PhoneBookMdo() {
        //
    }

    public void addPhone(UserPhone userPhone) {
        //
        phoneList.add(userPhone);
    }

    public boolean existPhone(String phoneNumber) {
        //
        UserPhone phone = findPhone(phoneNumber);
        if (phone != null) {
            return true;
        }

        return false;
    }

    public void removePhone(String phoneNumber) {
        //
        UserPhone targetPhone = null;

        for(UserPhone userPhone : phoneList) {
            //
            if (userPhone.getPhoneNumber().equals(phoneNumber)) {
                targetPhone =  userPhone;
                break;
            }
        }

        if (targetPhone != null) {
            phoneList.remove(targetPhone);
        }
    }

    public UserPhone findPhone(String phoneNumber) {
        //
        for(UserPhone phone : phoneList) {
            //
            if (phone.getPhoneNumber().equals(phoneNumber)) {
                return phone;
            }
        }

        return null;
    }
}