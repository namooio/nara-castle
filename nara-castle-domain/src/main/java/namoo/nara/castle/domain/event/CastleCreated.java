package namoo.nara.castle.domain.event;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.event.type.LocalEvent;

public class CastleCreated extends LocalEvent {
    //
    private Castle castle;
    private MetroEnrollment enrollment;

    public CastleCreated(Castle castle, MetroEnrollment enrollment) {
        //
        super(CastleCreated.class.getName());

        this.castle = castle;
        this.enrollment = enrollment;
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public MetroEnrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(MetroEnrollment enrollment) {
        this.enrollment = enrollment;
    }
}
