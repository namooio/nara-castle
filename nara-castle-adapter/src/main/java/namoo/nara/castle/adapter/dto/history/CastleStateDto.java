package namoo.nara.castle.adapter.dto.history;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastleStateDto {
    //
    private String currentState;
    private String targetState;
    private String remarks;
    private long modifiedTime;

    public CastleStateDto() {
        //
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
