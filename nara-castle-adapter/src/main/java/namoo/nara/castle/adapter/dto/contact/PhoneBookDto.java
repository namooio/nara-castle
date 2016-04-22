package namoo.nara.castle.adapter.dto.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class PhoneBookDto {
    //
    private List<UserPhoneDto> phones;

    public PhoneBookDto() {
        //
    }

    public void addPhoneDto(UserPhoneDto userPhoneDto) {
        //
        if (phones == null) {
            phones = new ArrayList<>();
        }
        phones.add(userPhoneDto);
    }

    public List<UserPhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(List<UserPhoneDto> phones) {
        this.phones = phones;
    }
}
