package namoo.nara.castle.rep.dto;

import namoo.nara.castle.rep.dto.contact.AddressBookDto;
import namoo.nara.castle.rep.dto.contact.EmailBookDto;
import namoo.nara.castle.rep.dto.contact.NameBookDto;
import namoo.nara.castle.rep.dto.contact.PhoneBookDto;
import namoo.nara.castle.rep.dto.history.AccountBookDto;
import namoo.nara.castle.rep.dto.history.CastleStateBookDto;
import namoo.nara.castle.rep.dto.history.MetroBookDto;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by hkkang on 2016-05-20.
 */
public class CastleFindDto implements Serializable {
    //
    private static final long serialVersionUID = -6180984375564403350L;

    private String id;
    private String name;
    private Locale locale;
    private String state;
    private Long buildTime;

    private CastellanFindDto owner;

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

    public Long getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Long buildTime) {
        this.buildTime = buildTime;
    }

    public CastellanFindDto getOwner() {
        return owner;
    }

    public void setOwner(CastellanFindDto owner) {
        this.owner = owner;
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
