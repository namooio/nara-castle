package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.PhoneBook;
import namoo.nara.castle.domain.entity.contact.UserPhone;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookMdo {
    //
    private List<UserPhoneMdo> phoneMdoList;

    public PhoneBookMdo() {
        //
    }

    public static PhoneBookMdo newInstance(PhoneBook phoneBook) {
        //
        PhoneBookMdo phoneBookMdo = new PhoneBookMdo();
        List<UserPhone> userPhones = phoneBook.findAll();
        if (userPhones != null) {
            for(UserPhone userPhone : userPhones) {
                phoneBookMdo.addUserPhoneMdo(UserPhoneMdo.newInstance(userPhone));
            }
        }
        return phoneBookMdo;
    }

    public PhoneBook getDomain() {
        //
        PhoneBook phoneBook = new PhoneBook();
        if (phoneMdoList != null) {
            for(UserPhoneMdo userPhoneMdo : phoneMdoList) {
                phoneBook.add(userPhoneMdo.getDomain());
            }
        }
        return phoneBook;
    }

    public void addUserPhoneMdo(UserPhoneMdo userPhoneMdo) {
        if (phoneMdoList == null) phoneMdoList = new ArrayList<>();
        phoneMdoList.add(userPhoneMdo);
    }

    public List<UserPhoneMdo> getPhoneMdoList() {
        return phoneMdoList;
    }

    public void setPhoneMdoList(List<UserPhoneMdo> phoneMdoList) {
        this.phoneMdoList = phoneMdoList;
    }
}