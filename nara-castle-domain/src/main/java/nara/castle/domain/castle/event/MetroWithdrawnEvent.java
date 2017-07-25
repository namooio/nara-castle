package nara.castle.domain.castle.event;

import nara.castle.domain.castle.entity.Enrollment;
import nara.share.domain.event.NaraEvent;

public class MetroWithdrawnEvent implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private Enrollment withdrawnEnrollment;

    public MetroWithdrawnEvent() {
        //
    }

    public MetroWithdrawnEvent(Enrollment withdrawnEnrollment) {
        //
        this.withdrawnEnrollment = withdrawnEnrollment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("withdrawnEnrollment:").append(withdrawnEnrollment);
        sb.append('}');
        return sb.toString();
    }

    public Enrollment getWithdrawnEnrollment() {
        return withdrawnEnrollment;
    }

    public void setWithdrawnEnrollment(Enrollment withdrawnEnrollment) {
        this.withdrawnEnrollment = withdrawnEnrollment;
    }
}