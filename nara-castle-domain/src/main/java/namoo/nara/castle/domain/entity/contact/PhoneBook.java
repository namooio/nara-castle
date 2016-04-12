package namoo.nara.castle.domain.entity.contact;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook { 
    //
    private List<UserPhone> phoneList = new ArrayList<>();

    public PhoneBook() {
        //
    }

    public void clear() {
        phoneList.clear();
    }

    public void add(UserPhone userPhone) {
        //
        phoneList.add(userPhone);
    }

    public boolean exist(String phoneNumber) {
        //
        UserPhone phone = find(phoneNumber);
        return phone != null;
    }

    public void remove(String phoneNumber) {
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

    public UserPhone find(String phoneNumber) {
        //
        for(UserPhone phone : phoneList) {
            //
            if (phone.getPhoneNumber().equals(phoneNumber)) {
                return phone;
            }
        }

        return null;
    }

    public List<UserPhone> findAll() {
        //
        return phoneList;
    }
}