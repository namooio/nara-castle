package namoo.nara.castle.domain.entity.contact;

import java.util.Locale;

public class UserAddress {
    //
    private AddressStyle style;         // Korean or US
    private String addressPartOne;      // street, P.O. Box, company
    private String addressPartTwo;      // apartment, suite, building, etc
    private String city;                //
    private String state;               // state, province, region
    private String zipCode;
    private String country;

    private String title;               // example: Home address
    private String phoneNumber;         // optional
    private String langCode;            // mandatory

    public UserAddress() {
        //
    }

    protected  UserAddress(AddressStyle style, String langCode) {
        //
        this.style = style;
        this.langCode = langCode;
    }

    public static UserAddress newKoreanAddress(String zipCode, String addressPartOne) {
        //
        UserAddress newAddress = new UserAddress(AddressStyle.Korean, Locale.KOREAN.getLanguage());
        newAddress.setZipCode(zipCode);
        newAddress.setAddressPartOne(addressPartOne);

        return newAddress;
    }

    public static UserAddress newUsAddress(String zipCode, String state, String city, String street) {
        //
        UserAddress newAddress = new UserAddress(AddressStyle.US, Locale.US.getLanguage());
        newAddress.setZipCode(zipCode);
        newAddress.setState(state);
        newAddress.setCity(city);
        newAddress.setAddressPartOne(street);

        return newAddress;
    }

    public AddressStyle getStyle() {
        return style;
    }

    public void setStyle(AddressStyle style) {
        this.style = style;
    }

    public String getAddressPartOne() {
        return addressPartOne;
    }

    public void setAddressPartOne(String addressPartOne) {
        this.addressPartOne = addressPartOne;
    }

    public String getAddressPartTwo() {
        return addressPartTwo;
    }

    public void setAddressPartTwo(String addressPartTwo) {
        this.addressPartTwo = addressPartTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public enum AddressStyle {
        //
        Korean,
        US
    }
}