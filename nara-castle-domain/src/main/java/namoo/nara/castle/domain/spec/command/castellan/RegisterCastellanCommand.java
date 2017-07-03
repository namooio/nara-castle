package namoo.nara.castle.domain.spec.command.castellan;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.domain.protocol.NaraCommand;

public class RegisterCastellanCommand implements NaraCommand {
    //
    private MetroEnrollment metroEnrollment;

    public RegisterCastellanCommand(MetroEnrollment metroEnrollment) {
        //
        this.metroEnrollment = metroEnrollment;
    }

    public MetroEnrollment getMetroEnrollment() {
        return metroEnrollment;
    }
}
