package namoo.nara.castle.adapter.dto.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class AddressBookDto extends ArrayList<UserAddressDto> {
    //
//    private List<UserAddressDto> addressDtoList;

    public AddressBookDto() {
        //
    }

    public void addAddressDto(UserAddressDto addressDto) {
        //
//        if (addressDtoList == null) {
//            addressDtoList = new ArrayList<>();
//        }
//        addressDtoList.add(addressDto);
        this.add(addressDto);
    }

//    public List<UserAddressDto> getAddressDtoList() {
//        return addressDtoList;
//    }
//
//    public void setAddressDtoList(List<UserAddressDto> addressDtoList) {
//        this.addressDtoList = addressDtoList;
//    }
}
