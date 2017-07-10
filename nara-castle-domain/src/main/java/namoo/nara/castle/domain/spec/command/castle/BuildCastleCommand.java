package namoo.nara.castle.domain.spec.command.castle;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.domain.protocol.NaraCommand;

public class BuildCastleCommand implements NaraCommand {
    //
    private MetroEnrollment enrollment;

    public BuildCastleCommand() {
        //
    }

    public BuildCastleCommand(MetroEnrollment enrollment) {
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
