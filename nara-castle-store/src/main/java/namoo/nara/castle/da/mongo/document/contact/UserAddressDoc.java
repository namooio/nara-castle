package namoo.nara.castle.da.mongo.document.contact;

public class UserAddressDoc {
    //
    private String style;                   // Korean or US.
    private String addressPartOne;          // street, P.O. Box, company
    private String addressPartTwo;          // apartment, suite, building, etc
    private String city;                    //
    private String state;                   // state, province, region
    private String zipCode;
    private String country;

    private String title;                   // example: Home address
    private String phoneNumber;             // optional
    private String langCode;                // mandatory

    public UserAddressDoc() {
        //
    }

    public static UserAddressDoc newInstance(UserAddress userAddress) {
        //
        UserAddressDoc userAddressDoc = new UserAddressDoc();
        userAddressDoc.setStyle(userAddress.getStyle().name());
        userAddressDoc.setAddressPartOne(userAddress.getAddressPartOne());
        userAddressDoc.setAddressPartTwo(userAddress.getAddressPartTwo());
        userAddressDoc.setCity(userAddress.getCity());
        userAddressDoc.setState(userAddress.getState());
        userAddressDoc.setZipCode(userAddress.getZipCode());
        userAddressDoc.setCountry(userAddress.getCountry());

        userAddressDoc.setTitle(userAddress.getTitle());
        userAddressDoc.setPhoneNumber(userAddress.getPhoneNumber());
        userAddressDoc.setLangCode(userAddress.getLangCode());
        return userAddressDoc;
    }

    public UserAddress toDomain() {
        //
        UserAddress userAddress = new UserAddress();
        userAddress.setStyle(UserAddress.AddressStyle.valueOf(style));
        userAddress.setAddressPartOne(addressPartOne);
        userAddress.setAddressPartTwo(addressPartTwo);
        userAddress.setCity(city);
        userAddress.setState(state);
        userAddress.setZipCode(zipCode);
        userAddress.setCountry(country);

        userAddress.setTitle(title);
        userAddress.setPhoneNumber(phoneNumber);
        userAddress.setLangCode(langCode);
        return userAddress;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
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

}