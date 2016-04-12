package namoo.nara.castle.da.mongo.mdo.history;

import namoo.nara.castle.domain.entity.history.CastleState;
import namoo.nara.castle.domain.entity.history.CastleStateBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleStateBookMdo {

    private List<CastleStateMdo> castleStateMdoList;

    public CastleStateBookMdo() {
        //
    }

    public static CastleStateBookMdo newInstance(CastleStateBook castleStateBook) {
        //
        CastleStateBookMdo castleStateBookMdo = new CastleStateBookMdo();
        List<CastleState> castleStates = castleStateBook.findAll();
        if (castleStates != null) {
            for(CastleState castleState : castleStates) {
                castleStateBookMdo.addCastleStateMdo(CastleStateMdo.newInstance(castleState));
            }
        }
        return castleStateBookMdo;
    }

    public CastleStateBook toDomain() {
        //
        CastleStateBook castleStateBook = new CastleStateBook();
        if (castleStateMdoList != null) {
            for(CastleStateMdo castleStateMdo : castleStateMdoList) {
                castleStateBook.attachCastleState(castleStateMdo.toDomain());
            }
        }
        return castleStateBook;
    }


    public void addCastleStateMdo(CastleStateMdo castleStateMdo) {
        if (castleStateMdoList == null) castleStateMdoList = new ArrayList<>();
        castleStateMdoList.add(castleStateMdo);
    }

    public List<CastleStateMdo> getCastleStateMdoList() {
        return castleStateMdoList;
    }

    public void setCastleStateMdoList(List<CastleStateMdo> castleStateMdoList) {
        this.castleStateMdoList = castleStateMdoList;
    }
}
