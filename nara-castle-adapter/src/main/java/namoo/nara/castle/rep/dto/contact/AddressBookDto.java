package namoo.nara.castle.rep.dto.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class AddressBookDto implements Serializable {
    //
    private static final long serialVersionUID = 1145258374083343745L;

    private List<UserAddressDto> addresses = new ArrayList<>();

    public AddressBookDto() {
        //
    }

    public void addAddressDto(UserAddressDto addressDto) {
        //
        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        addresses.add(addressDto);
    }

    public List<UserAddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<UserAddressDto> addresses) {
        this.addresses = addresses;
    }
}
