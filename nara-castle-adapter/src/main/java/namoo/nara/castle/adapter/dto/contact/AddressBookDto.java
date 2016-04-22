package namoo.nara.castle.adapter.dto.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class AddressBookDto {
    //
    private List<UserAddressDto> addresses;

    public AddressBookDto() {
        //
    }

    public void addAddressDto(UserAddressDto addressDto) {
        //
        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        addresses.add(addressDto);
        addresses.add(addressDto);
    }

    public List<UserAddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<UserAddressDto> addresses) {
        this.addresses = addresses;
    }
}
