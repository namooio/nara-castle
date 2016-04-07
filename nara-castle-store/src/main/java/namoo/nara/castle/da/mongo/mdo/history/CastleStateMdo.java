package namoo.nara.castle.da.mongo.mdo.history;

import namoo.nara.castle.domain.entity.OpenState;
import namoo.nara.castle.domain.entity.history.CastleState;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleStateMdo {
    //
    private OpenState currentState;
    private OpenState targetState;
    private String remarks;
    private long modifiedTime;

    public static CastleStateMdo newInstance(CastleState castleState) {
        //
        CastleStateMdo castleStateMdo = new CastleStateMdo();
        castleStateMdo.setCurrentState(castleState.getCurrentState());
        castleStateMdo.setTargetState(castleState.getTargetState());
        castleStateMdo.setRemarks(castleState.getRemarks());
        castleStateMdo.setModifiedTime(castleState.getModifiedTime());
        return castleStateMdo;
    }

    public CastleState getDomain() {
        //
        CastleState castleState = new CastleState();
        castleState.setCurrentState(currentState);
        castleState.setTargetState(targetState);
        castleState.setRemarks(remarks);
        castleState.setModifiedTime(modifiedTime);
        return castleState;
    }

    public OpenState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(OpenState currentState) {
        this.currentState = currentState;
    }

    public OpenState getTargetState() {
        return targetState;
    }

    public void setTargetState(OpenState targetState) {
        this.targetState = targetState;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
