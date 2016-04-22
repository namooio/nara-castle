package namoo.nara.castle.adapter.dto.contact;

import namoo.nara.castle.domain.entity.contact.PhoneBook;
import namoo.nara.castle.domain.entity.contact.UserPhone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class PhoneBookDto extends ArrayList<UserPhoneDto> {
    //
//    private List<UserPhoneDto> phoneDtoList;

    public PhoneBookDto() {
        //
    }

    public void addPhoneDto(UserPhoneDto userPhoneDto) {
        //
//        if (phoneDtoList == null) {
//            phoneDtoList = new ArrayList<>();
//        }
//        phoneDtoList.add(userPhoneDto);
        this.add(userPhoneDto);
    }
//
//    public List<UserPhoneDto> getPhoneDtoList() {
//        return phoneDtoList;
//    }
//
//    public void setPhoneDtoList(List<UserPhoneDto> phoneDtoList) {
//        this.phoneDtoList = phoneDtoList;
//    }
}
