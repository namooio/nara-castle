package nara.castle.domain.entity;

import nara.castle.domain.context.CastleIdBuilder;
import nara.castle.domain.spec.event.castle.CastleBuilt;
import nara.castle.domain.spec.event.castle.CastleModified;
import nara.castle.domain.spec.event.castle.MetroEnrolled;
import nara.castle.domain.spec.event.castle.MetroWithdrawn;
import nara.share.domain.Aggregate;
import nara.share.domain.Entity;
import nara.share.domain.NameValueList;
import nara.share.domain.event.NaraEvent;
import nara.share.domain.granule.NaraZone;
import nara.share.exception.NaraException;
import nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class Castle extends Entity implements Aggregate {
    //
    private String startNationId;
    private String name;
    private String primaryEmail;
    private NaraZone zone;
    private Long builtTime;

    private List<MetroEnrollment> enrollments;

    public Castle() {
        //
    }

    public Castle(String id) {
        //
        super(id);
        this.builtTime = System.currentTimeMillis();
        this.enrollments = new ArrayList<>();
    }

    public Castle(String id, MetroEnrollment enrollment) {
        //
        this(id);
        startNationId = enrollment.getNationId();
        name = enrollment.getName().getDisplayName();
        primaryEmail = enrollment.getEmail();
        zone = enrollment.getZone();
        enrollments.add(enrollment);
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
        String castleId = CastleIdBuilder.requestCastleId(sequence);

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

    public boolean isEnrolledMetro(String metroId, String civilianId) {
        //
        return enrollments
                .stream()
                .filter(enrollment -> metroId.equals(enrollment.getMetroId()) && civilianId.equals(enrollment.getCivilianId()))
                .findFirst()
                .isPresent();
    }

    @Override
    public void apply(NaraEvent event) {
        //
        if (event instanceof CastleBuilt) {
            CastleBuilt castleBuilt = (CastleBuilt) event;
            Castle castle = castleBuilt.getCastle();
            startNationId = castle.getStartNationId();
            name = castle.getName();
            primaryEmail = castle.getPrimaryEmail();
            zone = castle.getZone();
            enrollments = castle.getEnrollments();
        }
        else if (event instanceof CastleModified) {
            CastleModified castleModified = (CastleModified) event;
            setValues(castleModified.getNameValues());
        }
        else if (event instanceof MetroEnrolled) {
            MetroEnrolled metroEnrolled = (MetroEnrolled) event;
            enrollments.add(metroEnrolled.getEnrollment());
        }
        else if (event instanceof MetroWithdrawn) {
            MetroWithdrawn metroWithdrawn = (MetroWithdrawn) event;
            String metroId = metroWithdrawn.getMetroId();
            String civilianId = metroWithdrawn.getCivilianId();
            MetroEnrollment metroEnrollment = enrollments
                    .stream()
                    .filter(enrollment -> enrollment.getMetroId().equals(metroId) && enrollment.getCivilianId().equals(civilianId))
                    .findFirst()
                    .orElse(null);

            if (metroEnrollment == null) throw new NaraException(String.format("Metro enrollment for %s not found.", event));
            metroEnrollment.withdraw();
        }
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