package namoo.nara.castle.rep.dto.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class NameBookDto implements Serializable {
    //
    private static final long serialVersionUID = 1589916944571746718L;

    private List<UserNameDto> names = new ArrayList<>();

    public NameBookDto() {
        //
    }

    public void addNameDto(UserNameDto userNameDto) {
        if (names == null) {
            names = new ArrayList<>();
        }
        names.add(userNameDto);
    }


    public List<UserNameDto> getNames() {
        return names;
    }

    public void setNames(List<UserNameDto> names) {
        this.names = names;
    }

}
