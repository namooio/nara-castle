package namoo.nara.castle.adapter.dto.contact;

import namoo.nara.castle.domain.entity.contact.NameBook;
import namoo.nara.castle.domain.entity.contact.UserName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class NameBookDto extends ArrayList<UserNameDto> {
    //
    private String castleId;

//    private List<UserNameDto> nameDtoList;

    public NameBookDto() {
        //
    }

    public NameBook toDomain() {
        //
        NameBook nameBook = new NameBook();
//        if (super.nameDtoList != null) {
            for(UserNameDto userNameDto : this) {
                nameBook.add(userNameDto.toDomain());
            }
//        }
        return nameBook;
    }

    public static NameBookDto newInstance(NameBook nameBook) {
        //
        NameBookDto nameBookDto = new NameBookDto();
        List<UserName> nameList = nameBook.findAll();
        if (nameList != null) {
            for(UserName userName : nameList) {
                nameBookDto.addNameDto(UserNameDto.newInstance(userName));
            }
        }
        return nameBookDto;
    }

    private void addNameDto(UserNameDto userNameDto) {
//        if (nameDtoList == null) {
//            nameDtoList = new ArrayList<>();
//        }
//        nameDtoList.add(userNameDto);
        this.add(userNameDto);
    }
    /*
    public List<UserNameDto> getNameDtoList() {
        return nameDtoList;
    }

    public void setNameDtoList(List<UserNameDto> nameDtoList) {
        this.nameDtoList = nameDtoList;
    }
    */

    public String getCastleId() {
        return castleId;
    }

    public void setCastleId(String castleId) {
        this.castleId = castleId;
    }
}
