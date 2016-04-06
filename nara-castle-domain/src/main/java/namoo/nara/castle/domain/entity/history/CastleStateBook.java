package namoo.nara.castle.domain.entity.history;

import java.util.ArrayList;
import java.util.List;

public class CastleStateBook {

    private List<CastleState> castleStateList = new ArrayList<>();

    public CastleStateBook() {
        //
    }

    public void addCastleState(CastleState castleState) {
        //
        castleStateList.add(castleState);
    }

    public void removeCastleState(CastleState castleState) {
        //
        castleStateList.remove(castleState);
    }

    public List<CastleState> findAll() {
        //
        return castleStateList;
    }
}