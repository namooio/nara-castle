package namoo.nara.castle.adapter.dto.contact;

import namoo.nara.castle.domain.entity.contact.UserPhone;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class UserPhoneDto {
    //
    private String phoneNumber;     //+82-10-9202-9989
    private String countryCode;     //+82
    private String areaCode;        // 10
    private String number;          // 9202-9989

    public UserPhoneDto() {
        //
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

    public static UserPhoneDto newInstance(UserPhone userPhone) {
        //
        UserPhoneDto userPhoneDto = new UserPhoneDto();
        userPhoneDto.setPhoneNumber(userPhone.getPhoneNumber());
        userPhoneDto.setCountryCode(userPhone.getCountryCode());
        userPhoneDto.setAreaCode(userPhone.getAreaCode());
        userPhoneDto.setNumber(userPhone.getNumber());
        return userPhoneDto;
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
