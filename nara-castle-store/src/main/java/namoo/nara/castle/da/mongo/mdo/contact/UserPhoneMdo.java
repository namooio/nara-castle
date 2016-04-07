package namoo.nara.castle.da.mongo.mdo.contact;

public class UserPhoneMdo {
    //
    private String phoneNumber;
    private String areaCode;

    public UserPhoneMdo() {
        //
    }

    public UserPhoneMdo(String phoneNumber) {
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