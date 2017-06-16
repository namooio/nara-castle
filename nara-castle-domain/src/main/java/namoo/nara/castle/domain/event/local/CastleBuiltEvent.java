package namoo.nara.castle.domain.event.local;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.event.NaraEvent;

public class CastleBuiltEvent implements NaraEvent {
    //
    private Castle castle;
    private MetroEnrollment enrollment;

    public CastleBuiltEvent() {
        //
    }

    public CastleBuiltEvent(Castle castle, MetroEnrollment enrollment) {
        //
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
