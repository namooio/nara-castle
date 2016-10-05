package namoo.nara.castle.da.mongo.document.contact;

public class UserPhoneDoc {
    //
    private String phoneNumber;     //+82-10-9202-9989
    private String countryCode;     //+82
    private String areaCode;        // 10
    private String number;          // 9202-9989

    public UserPhoneDoc() {
        //
    }

    public static UserPhoneDoc newInstance(UserPhone userPhone) {
        //
        UserPhoneDoc userPhoneDoc = new UserPhoneDoc();
        userPhoneDoc.setPhoneNumber(userPhone.getPhoneNumber());
        userPhoneDoc.setCountryCode(userPhone.getCountryCode());
        userPhoneDoc.setAreaCode(userPhone.getAreaCode());
        userPhoneDoc.setNumber(userPhone.getNumber());
        return userPhoneDoc;
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