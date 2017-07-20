package nara.castle.domain.castle.command;

import nara.castle.domain.castle.entity.Enrollment;
import nara.share.domain.protocol.NaraCommand;

public class EnrollMetroCommand implements NaraCommand {
    //
    private String castellanId;
    private Enrollment enrollment;

    public EnrollMetroCommand() {
        //
    }

    public EnrollMetroCommand(String castellanId, Enrollment enrollment) {
        //
        this.castellanId = castellanId;
        this.enrollment = enrollment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("castellanId:'").append(castellanId).append('\'');
        sb.append(", enrollment:").append(enrollment);
        sb.append('}');
        return sb.toString();
    }

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

}
