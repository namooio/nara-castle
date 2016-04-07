package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.UserPhone;

public class UserPhoneMdo {
    //
    private String phoneNumber;
    private String areaCode;

    public UserPhoneMdo() {
        //
    }

    public static UserPhoneMdo newInstance(UserPhone userPhone) {
        //
        UserPhoneMdo userPhoneMdo = new UserPhoneMdo();
        userPhoneMdo.setPhoneNumber(userPhone.getPhoneNumber());
        userPhoneMdo.setAreaCode(userPhone.getAreaCode());
        return userPhoneMdo;
    }

    public UserPhone getDomain() {
        //
        UserPhone userPhone = new UserPhone();
        userPhone.setPhoneNumber(phoneNumber);
        userPhone.setAreaCode(areaCode);
        return userPhone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

}