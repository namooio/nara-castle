package namoo.nara.castle.da.mongo.mdo.history;

import namoo.nara.castle.domain.entity.OpenState;
import namoo.nara.castle.domain.entity.history.CastleState;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleStateMdo {
    //
    private String currentState;
    private String targetState;
    private String remarks;
    private long modifiedTime;

    public static CastleStateMdo newInstance(CastleState castleState) {
        //
        CastleStateMdo castleStateMdo = new CastleStateMdo();
        castleStateMdo.setCurrentState(castleState.getCurrentState().name());
        castleStateMdo.setTargetState(castleState.getTargetState().name());
        castleStateMdo.setRemarks(castleState.getRemarks());
        castleStateMdo.setModifiedTime(castleState.getModifiedTime());
        return castleStateMdo;
    }

    public CastleState toDomain() {
        //
        CastleState castleState = new CastleState();
        castleState.setCurrentState(OpenState.valueOf(currentState));
        castleState.setTargetState(OpenState.valueOf(targetState));
        castleState.setRemarks(remarks);
        castleState.setModifiedTime(modifiedTime);
        return castleState;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getTargetState() {
        return targetState;
    }

    public void setTargetState(String targetState) {
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
