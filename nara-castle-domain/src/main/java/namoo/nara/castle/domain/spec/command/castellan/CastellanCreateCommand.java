package namoo.nara.castle.domain.spec.command.castellan;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.domain.protocol.NaraCommand;

public class CastellanCreateCommand implements NaraCommand {
    //
    private MetroEnrollment metroEnrollment;

    public CastellanCreateCommand(MetroEnrollment metroEnrollment) {
        //
        this.metroEnrollment = metroEnrollment;
    }

    public MetroEnrollment getMetroEnrollment() {
        return metroEnrollment;
    }
}
