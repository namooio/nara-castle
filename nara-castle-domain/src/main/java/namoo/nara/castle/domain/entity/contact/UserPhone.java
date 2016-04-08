package namoo.nara.castle.domain.entity.contact;

public class UserPhone {
    //
    private String phoneNumber;     //+82-10-9202-9989
    private String countryCode;     //+82
    private String areaCode;        // 10
    private String number;          // 9202-9989

    public UserPhone() {
        //
    }

    public UserPhone (String phoneNumber) {
        //
        this.phoneNumber = phoneNumber;
        // TODO parsing and fill the other attributes
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}