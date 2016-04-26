package namoo.nara.castle.adapter.dto.history;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastleStateBookDto {
    //
    private List<CastleStateDto> states;

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
