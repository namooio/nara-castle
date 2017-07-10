package namoo.nara.castle.domain.spec.command.castellan;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.domain.protocol.NaraCommand;

public class RegisterCastellanCommand implements NaraCommand {
    //
    private MetroEnrollment enrollment;

    public RegisterCastellanCommand() {
        //
    }

    public RegisterCastellanCommand(MetroEnrollment enrollment) {
        //
        this.enrollment = enrollment;
    }

    public MetroEnrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(MetroEnrollment enrollment) {
        this.enrollment = enrollment;
    }
}
