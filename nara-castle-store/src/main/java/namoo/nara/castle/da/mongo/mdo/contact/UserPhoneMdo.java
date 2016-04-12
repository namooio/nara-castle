package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.UserPhone;

public class UserPhoneMdo {
    //
    private String phoneNumber;     //+82-10-9202-9989
    private String countryCode;     //+82
    private String areaCode;        // 10
    private String number;          // 9202-9989

    public UserPhoneMdo() {
        //
    }

    public static UserPhoneMdo newInstance(UserPhone userPhone) {
        //
        UserPhoneMdo userPhoneMdo = new UserPhoneMdo();
        userPhoneMdo.setPhoneNumber(userPhone.getPhoneNumber());
        userPhoneMdo.setCountryCode(userPhone.getCountryCode());
        userPhoneMdo.setAreaCode(userPhone.getAreaCode());
        userPhoneMdo.setNumber(userPhone.getNumber());
        return userPhoneMdo;
    }

    public UserPhone toDomain() {
        //
        UserPhone userPhone = new UserPhone();
        userPhone.setPhoneNumber(phoneNumber);
        userPhone.setCountryCode(countryCode);
        userPhone.setAreaCode(areaCode);
        userPhone.setNumber(number);
        return userPhone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}