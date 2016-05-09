package namoo.nara.castle.front.dto.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class AddressBookDto {
    //
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
