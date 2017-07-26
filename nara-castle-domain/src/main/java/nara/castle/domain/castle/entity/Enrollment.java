package nara.castle.domain.castle.entity;

import nara.share.domain.NameValueList;
import nara.share.domain.ValueObject;
import nara.share.domain.granule.Email;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import nara.share.util.json.JsonUtil;

public class Enrollment implements ValueObject {
    //
    private String metroId;
    private String citizenId;
    private Name name;
    private Email email;
    private boolean withdrawn;          // 탈퇴여부
    private NaraZone zone;
    private Long enrollingTime;
    private Long withdrawnTime;

    public Enrollment() {
        //
    }

    public Enrollment(String metroId, String citizenId, Name name, Email email, NaraZone zone) {
        //
        this.metroId = metroId;
        this.citizenId = citizenId;
        this.name = name;
        this.email = email;
        this.zone = zone;
        this.withdrawn = false;
        this.enrollingTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("metroId:'").append(metroId).append('\'');
        sb.append(", citizenId:'").append(citizenId).append('\'');
        sb.append(", name:").append(name);
        sb.append(", email:'").append(email).append('\'');
        sb.append(", withdrawn:").append(withdrawn);
        sb.append(", zone:").append(zone);
        sb.append(", enrollingTime:").append(enrollingTime);
        sb.append(", withdrawnTime:").append(withdrawnTime);
        sb.append('}');
        return sb.toString();
    }

    public static Enrollment getSample() {
        //
        String metroId = "POP";
        String citizenId = "12@POP";
        Name name = Name.getSample();
        Email email = Email.getSample();
        NaraZone zone = NaraZone.getSample();

        Enrollment sample = new Enrollment(metroId, citizenId, name, email, zone);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public Enrollment fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Enrollment.class);
    }

    public void withdraw() {
        //
        this.withdrawn = true;
        this.withdrawnTime = System.currentTimeMillis();
    }

    public void setValues(NameValueList nameValues) {
        //
        // Don't update any attributes except for "withdraw()"
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

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
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

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().getNationId());
    }
}