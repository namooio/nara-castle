package namoo.nara.castle.domain.spec.sdo;

import namoo.nara.share.domain.AbstractCdo;
import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.util.json.JsonUtil;

public class MetroEnrollmentCdo extends AbstractCdo {
    //
    private String metroId;
    private String civilianId;
    private Name name;
    private String email;

    public MetroEnrollmentCdo() {
        //
    }

    public MetroEnrollmentCdo(String metroId, String civilianId, Name name, String email) {
        //
        this.metroId = metroId;
        this.civilianId = civilianId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetroEnrollmentCdo{");
        sb.append("metroId='").append(metroId).append('\'');
        sb.append(", civilianId='").append(civilianId).append('\'');
        sb.append(", name=").append(name);
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static MetroEnrollmentCdo getSample() {
        //
        String metroId = "POP";
        String civilianId = "12@POP";
        Name name = Name.getSample();
        String email = "hong@gmail.com";

        MetroEnrollmentCdo sample = new MetroEnrollmentCdo(metroId, civilianId, name, email);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public MetroEnrollmentCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, MetroEnrollmentCdo.class);
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

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}