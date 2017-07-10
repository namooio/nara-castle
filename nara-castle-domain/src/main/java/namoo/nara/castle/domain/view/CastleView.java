package namoo.nara.castle.domain.view;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.domain.granule.NaraZone;

import java.util.List;

public class CastleView {
    //
    private String id;

    private String startNationId;
    private String name;
    private String primaryEmail;
    private NaraZone zone;
    private Long builtTime;

    private List<MetroEnrollment> enrollments;

    public CastleView() {
        //
    }

    public CastleView(Castle castle) {
        //
        this.id = castle.getId();
        this.startNationId = castle.getStartNationId();
        this.name = castle.getName();
        this.primaryEmail = castle.getPrimaryEmail();
        this.zone = castle.getZone();
        this.builtTime = castle.getBuiltTime();
        this.enrollments = castle.getEnrollments();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("id:'").append(id).append('\'');
        sb.append(", startNationId:'").append(startNationId).append('\'');
        sb.append(", name:'").append(name).append('\'');
        sb.append(", primaryEmail:'").append(primaryEmail).append('\'');
        sb.append(", zone:").append(zone);
        sb.append(", builtTime:").append(builtTime);
        sb.append(", enrollments:").append(enrollments);
        sb.append('}');
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartNationId() {
        return startNationId;
    }

    public void setStartNationId(String startNationId) {
        this.startNationId = startNationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public NaraZone getZone() {
        return zone;
    }

    public void setZone(NaraZone zone) {
        this.zone = zone;
    }

    public Long getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(Long builtTime) {
        this.builtTime = builtTime;
    }

    public List<MetroEnrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<MetroEnrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
