package namoo.nara.castle.da.mongo.mdo.contact;

import java.util.ArrayList;
import java.util.List;

public class AddressBookMdo {
    //
    private List<UserAddressMdo> addressList = new ArrayList<>();

    public AddressBookMdo(){
        //
    }

    public List<UserAddressMdo> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<UserAddressMdo> addressList) {
        this.addressList = addressList;
    }

    public void addAddress(UserAddressMdo address) {
        this.addressList.add(address);
    }
}