package namoo.nara.castle.adapter.dto.contact;

import java.util.ArrayList;

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

    public void addNameDto(UserNameDto userNameDto) {
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
