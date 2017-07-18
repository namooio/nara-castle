package nara.castle.domain.spec.event.castle;

import nara.castle.domain.entity.MetroEnrollment;
import nara.share.domain.event.NaraEvent;

public class MetroEnrolled implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private String castleId;
    private MetroEnrollment enrollment;

    public MetroEnrolled(String castleId, MetroEnrollment enrollment) {
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

    public MetroEnrollment getEnrollment() {
        return enrollment;
    }
}
