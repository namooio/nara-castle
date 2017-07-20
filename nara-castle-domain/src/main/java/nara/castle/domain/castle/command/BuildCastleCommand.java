package nara.castle.domain.castle.command;

import nara.castle.domain.castle.entity.Enrollment;
import nara.share.domain.protocol.NaraCommand;

public class BuildCastleCommand implements NaraCommand {
    //
    private Enrollment enrollment;

    public BuildCastleCommand() {
        //
    }

    public BuildCastleCommand(Enrollment enrollment) {
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

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }
}
