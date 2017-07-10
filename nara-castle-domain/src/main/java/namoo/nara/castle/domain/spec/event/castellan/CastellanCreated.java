package namoo.nara.castle.domain.spec.event.castellan;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.domain.event.NaraEvent;

public class CastellanCreated implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private String castellanId;
    private MetroEnrollment enrollment;

    public CastellanCreated(String castellanId, MetroEnrollment enrollment) {
        //
        this.castellanId = castellanId;
        this.enrollment = enrollment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("castellanId:'").append(castellanId).append('\'');
        sb.append(", enrollment:").append(enrollment);
        sb.append('}');
        return sb.toString();
    }

    public String getCastellanId() {
        return castellanId;
    }

    public MetroEnrollment getEnrollment() {
        return enrollment;
    }
}
