package namoo.nara.castle.adapter.dto.history;

import namoo.nara.castle.domain.entity.history.CastleState;
import namoo.nara.castle.domain.entity.history.CastleStateBook;

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

    public CastleStateBook toDomain() {
        //
        CastleStateBook castleStateBook = new CastleStateBook();
        if (castleStateDtos != null) {
            for(CastleStateDto castleStateDto : castleStateDtos) {
                castleStateBook.attachCastleState(castleStateDto.toDomain());
            }
        }

        return castleStateBook;
    }

    public static CastleStateBookDto newInstance(CastleStateBook castleStateBook) {
        //
        CastleStateBookDto castleStateBookDto = new CastleStateBookDto();
        List<CastleState> stateList = castleStateBook.findAll();
        if (stateList != null) {
            for(CastleState castleState : stateList) {
                castleStateBookDto.addStateDto(CastleStateDto.newInstance(castleState));
            }
        }
        return castleStateBookDto;
    }

    private void addStateDto(CastleStateDto castleStateDto) {
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
