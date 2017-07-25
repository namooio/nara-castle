package nara.castle.domain.castle.event;

import nara.castle.domain.castle.entity.Enrollment;
import nara.share.domain.event.NaraEvent;

public class MetroEnrolledEvent implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private String castellanId;
    private Enrollment enrollment;

    public MetroEnrolledEvent(String castellanId, Enrollment enrollment) {
        //
        this.castellanId = castellanId;
        this.enrollment = enrollment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetroEnrolledEvent{");
        sb.append("castellanId='").append(castellanId).append('\'');
        sb.append(", enrollment=").append(enrollment);
        sb.append('}');
        return sb.toString();
    }

    public String getCastellanId() {
        return castellanId;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }
}