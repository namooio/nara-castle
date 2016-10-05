package namoo.nara.castle.da.mongo.document.contact;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookDoc {
    //
    private List<UserPhoneDoc> phoneList;

    public PhoneBookDoc() {
        //
    }

    public static PhoneBookDoc newInstance(PhoneBook phoneBook) {
        //
        PhoneBookDoc phoneBookDoc = new PhoneBookDoc();
        List<UserPhone> userPhones = phoneBook.findAll();
        if (userPhones != null) {
            for(UserPhone userPhone : userPhones) {
                phoneBookDoc.addUserPhone(UserPhoneDoc.newInstance(userPhone));
            }
        }
        return phoneBookDoc;
    }

    public PhoneBook toDomain() {
        //
        PhoneBook phoneBook = new PhoneBook();
        if (phoneList != null) {
            for(UserPhoneDoc userPhoneDoc : phoneList) {
                phoneBook.add(userPhoneDoc.toDomain());
            }
        }
        return phoneBook;
    }

    public void addUserPhone(UserPhoneDoc userPhone) {
        if (phoneList == null) phoneList = new ArrayList<>();
        phoneList.add(userPhone);
    }

    public List<UserPhoneDoc> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<UserPhoneDoc> phoneList) {
        this.phoneList = phoneList;
    }
}