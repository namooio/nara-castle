package namoo.nara.castle.domain.entity.history;

import namoo.nara.castle.domain.entity.OpenState;

public class CastleState {
    //
    private OpenState currentState;
    private OpenState targetState;
    private String remarks;
    private long modifiedTime;

    public CastleState() {
        //
    }

    public CastleState(OpenState currentState, OpenState targetState, String remarks) {
        //
        this.currentState = currentState;
        this.targetState = targetState;
        this.remarks = remarks;
        this.modifiedTime = System.currentTimeMillis();
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
