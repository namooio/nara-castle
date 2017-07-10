package namoo.nara.castle.domain.spec.event.castellan;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.domain.event.NaraEvent;

public class CastellanCreated implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private MetroEnrollment enrollment;

    public CastellanCreated(MetroEnrollment enrollment) {
        //
        this.enrollment = enrollment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("enrollment:").append(enrollment);
        sb.append('}');
        return sb.toString();
    }

    public MetroEnrollment getEnrollment() {
        return enrollment;
    }
}
