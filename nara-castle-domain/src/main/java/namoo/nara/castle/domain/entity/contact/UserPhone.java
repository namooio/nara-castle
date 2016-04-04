package namoo.nara.castle.domain.entity.contact;

public class UserPhone {
    //
    private String phoneNumber;
    private String areaCode;

    public UserPhone() {
        //
    }

    public UserPhone (String phoneNumber) {
        //
        this.phoneNumber = phoneNumber;
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