package namoo.nara.castle.front.dto;

import namoo.nara.castle.front.dto.contact.AddressBookDto;
import namoo.nara.castle.front.dto.contact.EmailBookDto;
import namoo.nara.castle.front.dto.contact.NameBookDto;
import namoo.nara.castle.front.dto.contact.PhoneBookDto;
import namoo.nara.castle.front.dto.history.AccountBookDto;
import namoo.nara.castle.front.dto.history.CastleStateBookDto;
import namoo.nara.castle.front.dto.history.MetroBookDto;

import java.util.Date;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleFindDto {
    //
    private String id;
    private String name;
    private Locale locale;
    private String state;
    private Date  buildTime;

    private CastellanFindDto castellan;

    // TODO : 뷰모델(DTO) 구조 고민 필요 -> 도메인과 동일하게 갈 것인가..
    // Contact book
    private NameBookDto nameBook;
    private PhoneBookDto phoneBook;
    private EmailBookDto emailBook;
    private AddressBookDto addressBook;

    // History book
    private AccountBookDto accountBook;
    private CastleStateBookDto stateBook;
    private MetroBookDto metroBook;


    public CastleFindDto() {
        //
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public CastellanFindDto getCastellan() {
        return castellan;
    }

    public void setCastellan(CastellanFindDto castellan) {
        this.castellan = castellan;
    }

    public NameBookDto getNameBook() {
        return nameBook;
    }

    public void setNameBook(NameBookDto nameBook) {
        this.nameBook = nameBook;
    }

    public PhoneBookDto getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(PhoneBookDto phoneBook) {
        this.phoneBook = phoneBook;
    }

    public EmailBookDto getEmailBook() {
        return emailBook;
    }

    public void setEmailBook(EmailBookDto emailBook) {
        this.emailBook = emailBook;
    }

    public AddressBookDto getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBookDto addressBook) {
        this.addressBook = addressBook;
    }

    public AccountBookDto getAccountBook() {
        return accountBook;
    }

    public void setAccountBook(AccountBookDto accountBook) {
        this.accountBook = accountBook;
    }

    public CastleStateBookDto getStateBook() {
        return stateBook;
    }

    public void setStateBook(CastleStateBookDto stateBook) {
        this.stateBook = stateBook;
    }

    public MetroBookDto getMetroBook() {
        return metroBook;
    }

    public void setMetroBook(MetroBookDto metroBook) {
        this.metroBook = metroBook;
    }
}
