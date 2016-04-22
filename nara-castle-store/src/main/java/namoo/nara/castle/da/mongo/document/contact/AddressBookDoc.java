package namoo.nara.castle.da.mongo.document.contact;

import namoo.nara.castle.domain.entity.contact.AddressBook;
import namoo.nara.castle.domain.entity.contact.UserAddress;

import java.util.ArrayList;
import java.util.List;

public class AddressBookDoc {
    //
    private List<UserAddressDoc> addressList;

    public AddressBookDoc(){
        //
    }

    public static AddressBookDoc newInstance(AddressBook addressBook) {
        //
        AddressBookDoc addressBookDoc = new AddressBookDoc();
        List<UserAddress> userAddresses = addressBook.findAll();
        if (userAddresses != null) {
            for(UserAddress userAddress : userAddresses) {
                addressBookDoc.addAddress(UserAddressDoc.newInstance(userAddress));
            }
        }
        return addressBookDoc;
    }

    public AddressBook toDomain() {
        AddressBook addressBook = new AddressBook();
        if (addressList != null) {
            for(UserAddressDoc userAddressDoc : addressList) {
                addressBook.add(userAddressDoc.toDomain());
            }
        }
        return addressBook;
    }

    public List<UserAddressDoc> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<UserAddressDoc> addressList) {
        this.addressList = addressList;
    }

    public void addAddress(UserAddressDoc userAddress) {
        if (addressList == null) addressList = new ArrayList<>();
        this.addressList.add(userAddress);
    }
}