package nara.castle.domain.castle.command;

import nara.castle.domain.castle.entity.MetroEnrollment;
import nara.share.domain.protocol.NaraCommand;

public class EnrollMetroCommand implements NaraCommand {
    //
    private String castleId;
    private MetroEnrollment enrollment;

    public EnrollMetroCommand() {
        //
    }

    public EnrollMetroCommand(String castleId, MetroEnrollment enrollment) {
        //
        this.castleId = castleId;
        this.enrollment = enrollment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("castleId:'").append(castleId).append('\'');
        sb.append(", enrollment:").append(enrollment);
        sb.append('}');
        return sb.toString();
    }

    public String getCastleId() {
        return castleId;
    }

    public void setCastleId(String castleId) {
        this.castleId = castleId;
    }

    public MetroEnrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(MetroEnrollment enrollment) {
        this.enrollment = enrollment;
    }

}
