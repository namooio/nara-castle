package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.castle.domain.spec.event.castle.CastleModified;
import namoo.nara.castle.domain.spec.event.castle.MetroEnrolled;
import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.granule.NaraZone;
import namoo.nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class Castle extends Entity implements Aggregate {
    //
    private String startNationId;
    private String name;
    private String primaryEmail;
    private NaraZone zone;
    private Long builtTime;

    transient private List<MetroEnrollment> enrollments;       // weak association

    public Castle() {
        //
    }

    public Castle(String id) {
        //
        super(id);
    }

    public Castle(String castleId, MetroEnrollment metroEnrollment) {
        //
        super(castleId);
        this.startNationId = metroEnrollment.getNationId();
        this.name = metroEnrollment.getName().getDisplayName();
        this.primaryEmail = metroEnrollment.getEmail();
        this.zone = metroEnrollment.getZone();
        this.builtTime = System.currentTimeMillis();

        this.enrollments = new ArrayList<>();
        this.enrollments.add(metroEnrollment);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Castle{");
        sb.append(super.toString());
        sb.append(", startNationId='").append(startNationId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", primaryEmail='").append(primaryEmail).append('\'');
        sb.append(", zone=").append(zone);
        sb.append(", builtTime=").append(builtTime);
        sb.append(", enrollments=").append(enrollments);
        sb.append('}');
        return sb.toString();
    }

    public static Castle getSample() {
        //
        MetroEnrollment metroEnrollment = MetroEnrollment.getSample();
        long sequence = CastleBook.getSample().getSequence();
        String castleId = CastleIdBuilder.makeCastleId(sequence);

        Castle sample = new Castle(castleId, metroEnrollment);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static Castle fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Castle.class);
    }

    public void setValues(NameValueList nameValues) {
        //
        nameValues.getList().forEach(nameValue -> {
            if ("name".equals(nameValue.getName())) this.setName(nameValue.getValue());
            else if ("primaryEmail".equals(nameValue.getName())) this.setPrimaryEmail(nameValue.getValue());
        });
    }

    public void apply(CastleModified event) {
        //
        setValues(event.getNameValues());
    }

    public void apply(MetroEnrolled event) {
        //
        enrollments.add(event.getMetroEnrollment());
    }

    public Long getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(Long builtTime) {
        this.builtTime = builtTime;
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

    public List<MetroEnrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<MetroEnrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public NaraZone getZone() {
        return zone;
    }

    public void setZone(NaraZone zone) {
        this.zone = zone;
    }

    public static void main(String[] args) {
        //
        System.out.println(Castle.getSample());
    }

}