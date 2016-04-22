package namoo.nara.castle.da.mongo.document.history;

import namoo.nara.castle.domain.entity.history.CastleState;
import namoo.nara.castle.domain.entity.history.CastleStateBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleStateBookDoc {

    private List<CastleStateDoc> castleStateList;

    public CastleStateBookDoc() {
        //
    }

    public static CastleStateBookDoc newInstance(CastleStateBook castleStateBook) {
        //
        CastleStateBookDoc castleStateBookDoc = new CastleStateBookDoc();
        List<CastleState> castleStates = castleStateBook.findAll();
        if (castleStates != null) {
            for(CastleState castleState : castleStates) {
                castleStateBookDoc.addCastleState(CastleStateDoc.newInstance(castleState));
            }
        }
        return castleStateBookDoc;
    }

    public CastleStateBook toDomain() {
        //
        CastleStateBook castleStateBook = new CastleStateBook();
        if (castleStateList != null) {
            for(CastleStateDoc castleStateDoc : castleStateList) {
                castleStateBook.attachCastleState(castleStateDoc.toDomain());
            }
        }
        return castleStateBook;
    }


    public void addCastleState(CastleStateDoc castleState) {
        if (castleStateList == null) castleStateList = new ArrayList<>();
        castleStateList.add(castleState);
    }

    public List<CastleStateDoc> getCastleStateList() {
        return castleStateList;
    }

    public void setCastleStateList(List<CastleStateDoc> castleStateList) {
        this.castleStateList = castleStateList;
    }
}
