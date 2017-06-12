package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.util.json.JsonUtil;

import java.util.List;

public class Castle extends Entity implements Aggregate {
    //
    private String startNationId;
    private String name;
    private String primaryEmail;
    private Long builtTime;

    transient private Castellan castellan;
    transient private List<MetroEnrollment> enrollments;
    transient private IdentityPlate identityPlate;

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
        this.castellan = new Castellan(metroEnrollment);
        this.builtTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Castle{");
        sb.append("startNationId='").append(startNationId).append('\'');
        sb.append(", castellan=").append(castellan);
        sb.append(", builtTime=").append(builtTime);
        sb.append(", identityPlate=").append(identityPlate);
        sb.append('}');
        return sb.toString();
    }

    public static Castle getSample() {
        //
        MetroEnrollment metroEnrollment = MetroEnrollment.getSample();
        long sequence = CastleBook.getSample().nextSequence();
        String castleId = CastleContext.getCastleIdBuilder().makeCastleId(sequence);

        Castle castle = new Castle(castleId, metroEnrollment);

        return castle;
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

    public Castellan getCastellan() {
        return castellan;
    }

    public void setCastellan(Castellan castellan) {
        this.castellan = castellan;
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

    public IdentityPlate getIdentityPlate() {
        return identityPlate;
    }

    public void setIdentityPlate(IdentityPlate identityPlate) {
        this.identityPlate = identityPlate;
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

    public static void main(String[] args) {
        //
        System.out.println(Castle.getSample());
    }

}