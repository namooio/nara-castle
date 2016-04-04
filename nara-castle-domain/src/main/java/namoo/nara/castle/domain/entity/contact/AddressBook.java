package namoo.nara.castle.domain.entity.contact;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    //
    private List<UserAddress> addressList = new ArrayList<>();

    public AddressBook(){
        //
    }

    public void addAddress(UserAddress address) {
        //
        addressList.add(address);
    }

    public void removeAddress(UserAddress address) {
        //
        addressList.remove(address);
    }

    public List<UserAddress> findAll() {
        return addressList;
    }
}