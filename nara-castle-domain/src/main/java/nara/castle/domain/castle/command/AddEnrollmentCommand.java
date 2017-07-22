package nara.castle.domain.castle.command;

import nara.castle.domain.castle.entity.Enrollment;
import nara.share.domain.protocol.NaraCommand;

public class AddEnrollmentCommand implements NaraCommand {
    //
    private String castellanId;
    private Enrollment enrollment;

    public AddEnrollmentCommand() {
        //
    }

    public AddEnrollmentCommand(String castellanId, Enrollment enrollment) {
        //
        this.castellanId = castellanId;
        this.enrollment = enrollment;
    }

    @Override
    public String toString() {
        return "AddEnrollmentCommand{" +
                "castellanId='" + castellanId + '\'' +
                ", enrollment=" + enrollment +
                '}';
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
