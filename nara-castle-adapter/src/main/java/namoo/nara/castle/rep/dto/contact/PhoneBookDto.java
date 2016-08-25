package namoo.nara.castle.rep.dto.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class PhoneBookDto implements Serializable {
    //
    private static final long serialVersionUID = -6385486605435636239L;

    private List<UserPhoneDto> phones = new ArrayList<>();

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
