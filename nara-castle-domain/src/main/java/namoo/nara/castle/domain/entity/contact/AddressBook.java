package namoo.nara.castle.domain.entity.contact;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    //
    private List<UserAddress> addressList = new ArrayList<>();

    public AddressBook(){
        //
    }

    public int count() {
        //
        return addressList.size();
    }

    public void clear() {
        //
        addressList.clear();
    }

    public boolean existByTitle(String title) {
        //
        return findByTitle(title) != null;
    }

    public  void update(UserAddress address) {
        //
        UserAddress currentAddress = findByTitle(address.getTitle());
        if (currentAddress == null) {
            return;
        }

        currentAddress.updateWith(address);
    }

    public void add(UserAddress address) {
        //
        addressList.add(address);
    }

    public void removeByTitle(String title) {
        //
        UserAddress address = findByTitle(title);
        if (address != null) {
            remove(address);
        }
    }

    public void remove(UserAddress address) {
        //
        addressList.remove(address);
    }

    public UserAddress findByTitle(String title) {
        //
        for(UserAddress address : addressList) {
            if (address.getTitle().equals(title)) {
                return address;
            }
        }

        return null;
    }

    public List<UserAddress> findAll() {
        //
        return addressList;
    }
}