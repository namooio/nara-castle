package nara.castle.domain.castle.command;

import nara.castle.domain.castle.entity.MetroEnrollment;
import nara.share.domain.protocol.NaraCommand;

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

    public void setEnrollment(MetroEnrollment enrollment) {
        this.enrollment = enrollment;
    }
}
