package nara.castle.domain.castlequery.model;

import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.entity.Enrollment;
import nara.share.domain.Entity;
import nara.share.domain.granule.Email;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import nara.share.util.json.JsonUtil;

public class EnrollmentRM extends Entity {
    //
    private String metroId;
    private String civilianId;
    private Name name;
    private Email email;
    private boolean withdrawn;          // 탈퇴여부
    private NaraZone zone;
    private Long enrollingTime;
    private Long withdrawnTime;

    private String castellanId;

    public EnrollmentRM() {
        //
    }

    public EnrollmentRM(String castellanId, Enrollment enrollment) {
        //
        super();
        this.metroId = enrollment.getMetroId();
        this.civilianId = enrollment.getCivilianId();
        this.name = enrollment.getName();
        this.email = enrollment.getEmail();
        this.zone = enrollment.getZone();
        this.withdrawn = enrollment.isWithdrawn();
        this.enrollingTime = enrollment.getEnrollingTime();
        this.withdrawnTime = enrollment.getWithdrawnTime();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EnrollmentRM{");
        sb.append("metroId='").append(metroId).append('\'');
        sb.append(", civilianId='").append(civilianId).append('\'');
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", withdrawn=").append(withdrawn);
        sb.append(", zone=").append(zone);
        sb.append(", enrollingTime=").append(enrollingTime);
        sb.append(", withdrawnTime=").append(withdrawnTime);
        sb.append(", castellanId='").append(castellanId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static EnrollmentRM getSample() {
        //
        String castellanId = Castellan.getSample().getId();
        Enrollment enrollment = Enrollment.getSample();
        EnrollmentRM sample = new EnrollmentRM(castellanId, enrollment);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public EnrollmentRM fromJson(String json) {
        //
        return JsonUtil.fromJson(json, EnrollmentRM.class);
    }

    public String getNationId() {
        //
        return metroId.substring(0,1);
    }

    public String getMetroId() {
        return metroId;
    }

    public void setMetroId(String metroId) {
        this.metroId = metroId;
    }

    public String getCivilianId() {
        return civilianId;
    }

    public void setCivilianId(String civilianId) {
        this.civilianId = civilianId;
    }

    public boolean isWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(boolean withdrawn) {
        this.withdrawn = withdrawn;
    }

    public Long getEnrollingTime() {
        return enrollingTime;
    }

    public void setEnrollingTime(Long enrollingTime) {
        this.enrollingTime = enrollingTime;
    }

    public Long getWithdrawnTime() {
        return withdrawnTime;
    }

    public void setWithdrawnTime(Long withdrawnTime) {
        this.withdrawnTime = withdrawnTime;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public NaraZone getZone() {
        return zone;
    }

    public void setZone(NaraZone zone) {
        this.zone = zone;
    }

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().getNationId());
    }
}