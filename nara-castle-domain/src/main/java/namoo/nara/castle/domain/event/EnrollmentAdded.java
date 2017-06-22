package namoo.nara.castle.domain.event;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.event.type.LocalEvent;

public class EnrollmentAdded extends LocalEvent {
    //
    private MetroEnrollment enrollment;

    public EnrollmentAdded(MetroEnrollment enrollment) {
        //
        super(EnrollmentAdded.class.getName());
        this.enrollment = enrollment;
    }

    public MetroEnrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(MetroEnrollment enrollment) {
        this.enrollment = enrollment;
    }
}