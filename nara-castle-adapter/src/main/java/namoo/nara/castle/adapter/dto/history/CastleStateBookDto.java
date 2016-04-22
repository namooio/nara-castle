package namoo.nara.castle.adapter.dto.history;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastleStateBookDto {
    //
    private List<CastleStateDto> castleStateDtos;

    public CastleStateBookDto() {
        //
    }

    public void addStateDto(CastleStateDto castleStateDto) {
        //
        if (castleStateDtos == null) {
            castleStateDtos = new ArrayList<>();
        }
        castleStateDtos.add(castleStateDto);
    }

    public List<CastleStateDto> getCastleStateDtos() {
        return castleStateDtos;
    }

    public void setCastleStateDtos(List<CastleStateDto> castleStateDtos) {
        this.castleStateDtos = castleStateDtos;
    }

}
