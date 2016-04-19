package namoo.nara.castle.adapter.dto.contact;

import namoo.nara.castle.domain.entity.contact.PhoneBook;
import namoo.nara.castle.domain.entity.contact.UserPhone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class PhoneBookDto {
    //
    private List<UserPhoneDto> phoneDtoList;

    public PhoneBookDto() {
        //
    }

    public PhoneBook toDomain() {
        //
        PhoneBook phoneBook = new PhoneBook();
        if (phoneDtoList != null) {
            for(UserPhoneDto userPhoneDto : phoneDtoList) {
                phoneBook.add(userPhoneDto.toDomain());
            }
        }
        return phoneBook;
    }

    public static PhoneBookDto newInstance(PhoneBook phoneBook) {
        //
        PhoneBookDto phoneBookDto = new PhoneBookDto();
        List<UserPhone> phoneList = phoneBook.findAll();
        if (phoneList != null) {
            for(UserPhone userPhone : phoneList) {
                phoneBookDto.addPhoneDto(UserPhoneDto.newInstance(userPhone));
            }
        }
        return phoneBookDto;
    }

    private void addPhoneDto(UserPhoneDto userPhoneDto) {
        //
        if (phoneDtoList == null) {
            phoneDtoList = new ArrayList<>();
        }
        phoneDtoList.add(userPhoneDto);
    }

    public List<UserPhoneDto> getPhoneDtoList() {
        return phoneDtoList;
    }

    public void setPhoneDtoList(List<UserPhoneDto> phoneDtoList) {
        this.phoneDtoList = phoneDtoList;
    }
}
