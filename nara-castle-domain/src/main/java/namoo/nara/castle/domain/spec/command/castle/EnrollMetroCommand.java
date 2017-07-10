package namoo.nara.castle.domain.spec.command.castle;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.domain.granule.NaraZone;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.util.json.JsonUtil;

public class EnrollMetroCommand implements NaraCommand {
    //
    private MetroEnrollment enrollment;

    public EnrollMetroCommand() {
        //
    }

    public EnrollMetroCommand(MetroEnrollment enrollment) {
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

    public static EnrollMetroCommand getSample() {
        //
        String metroId = "POP";
        String civilianId = "12@POP";
        Name name = Name.getSample();
        String email = "hong@gmail.com";
        NaraZone zone = NaraZone.getSample();

        MetroEnrollment enrollment = new MetroEnrollment(metroId, civilianId, name, email, zone);
        EnrollMetroCommand sample = new EnrollMetroCommand(enrollment);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public EnrollMetroCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, EnrollMetroCommand.class);
    }

    public MetroEnrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(MetroEnrollment enrollment) {
        this.enrollment = enrollment;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}
