package namoo.nara.castle.adapter.dto.contact;

import namoo.nara.castle.domain.entity.contact.AddressBook;
import namoo.nara.castle.domain.entity.contact.UserAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class AddressBookDto {
    //
    private List<UserAddressDto> addressDtoList;

    public AddressBookDto() {
        //
    }

    public AddressBook toDomain() {
        //
        AddressBook addressBook = new AddressBook();
        if (addressDtoList != null) {
            for(UserAddressDto userAddressDto : addressDtoList) {
                addressBook.add(userAddressDto.toDomain());
            }
        }
        return addressBook;
    }

    public static AddressBookDto newInstance(AddressBook addressBook) {
        //
        AddressBookDto addressBookDto = new AddressBookDto();
        List<UserAddress> addressList = addressBook.findAll();
        if (addressList != null) {
            for(UserAddress userAddress : addressList) {
                addressBookDto.addAddressDto(UserAddressDto.newInstance(userAddress));
            }
        }
        return addressBookDto;
    }

    private void addAddressDto(UserAddressDto addressDto) {
        //
        if (addressDtoList == null) {
            addressDtoList = new ArrayList<>();
        }
        addressDtoList.add(addressDto);
    }

    public List<UserAddressDto> getAddressDtoList() {
        return addressDtoList;
    }

    public void setAddressDtoList(List<UserAddressDto> addressDtoList) {
        this.addressDtoList = addressDtoList;
    }
}
