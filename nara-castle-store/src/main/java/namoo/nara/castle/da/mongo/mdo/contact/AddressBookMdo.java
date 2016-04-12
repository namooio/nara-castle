package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.AddressBook;
import namoo.nara.castle.domain.entity.contact.UserAddress;

import java.util.ArrayList;
import java.util.List;

public class AddressBookMdo {
    //
    private List<UserAddressMdo> addressMdoList;

    public AddressBookMdo(){
        //
    }

    public static AddressBookMdo newInstance(AddressBook addressBook) {
        //
        AddressBookMdo addressBookMdo = new AddressBookMdo();
        List<UserAddress> userAddresses = addressBook.findAll();
        if (userAddresses != null) {
            for(UserAddress userAddress : userAddresses) {
                addressBookMdo.addAddressMdo(UserAddressMdo.newInstance(userAddress));
            }
        }
        return addressBookMdo;
    }

    public AddressBook getDomain() {
        AddressBook addressBook = new AddressBook();
        if (addressMdoList != null) {
            for(UserAddressMdo userAddressMdo : addressMdoList) {
                addressBook.add(userAddressMdo.getDomain());
            }
        }
        return addressBook;
    }

    public List<UserAddressMdo> getAddressMdoList() {
        return addressMdoList;
    }

    public void setAddressMdoList(List<UserAddressMdo> addressMdoList) {
        this.addressMdoList = addressMdoList;
    }

    public void addAddressMdo(UserAddressMdo userAddressMdo) {
        if (addressMdoList == null) addressMdoList = new ArrayList<>();
        this.addressMdoList.add(userAddressMdo);
    }
}