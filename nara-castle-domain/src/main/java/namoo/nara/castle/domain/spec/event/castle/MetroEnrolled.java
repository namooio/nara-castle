package namoo.nara.castle.domain.spec.event.castle;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.domain.event.NaraEvent;

public class MetroEnrolled implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private MetroEnrollment metroEnrollment;

    public MetroEnrolled(MetroEnrollment metroEnrollment) {
        //
        this.metroEnrollment = metroEnrollment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("metroEnrollment:").append(metroEnrollment);
        sb.append('}');
        return sb.toString();
    }

    public MetroEnrollment getMetroEnrollment() {
        return metroEnrollment;
    }
}
