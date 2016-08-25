package namoo.nara.castle.rep.dto.history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastleStateBookDto implements Serializable {
    //
    private static final long serialVersionUID = 273675709942964640L;

    private List<CastleStateDto> states = new ArrayList<>();

    public CastleStateBookDto() {
        //
    }

    public void addStateDto(CastleStateDto castleStateDto) {
        //
        if (states == null) {
            states = new ArrayList<>();
        }
        states.add(castleStateDto);
    }

    public List<CastleStateDto> getStates() {
        return states;
    }

    public void setStates(List<CastleStateDto> states) {
        this.states = states;
    }

}
