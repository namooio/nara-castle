package namoo.nara.castle.adapter.dto.contact;

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

    public void addPhoneDto(UserPhoneDto userPhoneDto) {
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
