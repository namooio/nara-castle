package nara.castle.da.mongo.document;

import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.share.domain.granule.Email;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Entity("CA_ENROLLMENT")
public class EnrollmentRMDoc {
    //
    @Id
    private String id;

    private String metroId;
    private String citizenId;
    private Name name;
    private Email email;
    private boolean withdrawn;          // 탈퇴여부
    private NaraZone zone;
    private Long enrollingTime;
    private Long withdrawnTime;

    @Indexed
    private String castellanId;

    public EnrollmentRMDoc() {
        //
    }

    public static EnrollmentRMDoc toDocument(EnrollmentRM enrollmentRM) {
        //
        EnrollmentRMDoc enrollmentRMDoc = new EnrollmentRMDoc();
        BeanUtils.copyProperties(enrollmentRM, enrollmentRMDoc);
        return enrollmentRMDoc;
    }

    public static List<EnrollmentRMDoc> toDocument(List<EnrollmentRM> enrollmentRMS) {
        //
        return enrollmentRMS.stream().map(enrollmentRM -> toDocument(enrollmentRM)).collect(Collectors.toList());
    }

    public static List<EnrollmentRM> toModel(List<EnrollmentRMDoc> enrollmentRMDocs) {
        //
        return enrollmentRMDocs.stream().map(doc -> doc.toModel()).collect(Collectors.toList());
    }

    public EnrollmentRM toModel() {
        //
        EnrollmentRM enrollmentRM = new EnrollmentRM(id);
        BeanUtils.copyProperties(this, enrollmentRM);
        return enrollmentRM;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(boolean withdrawn) {
        this.withdrawn = withdrawn;
    }

    public NaraZone getZone() {
        return zone;
    }

    public void setZone(NaraZone zone) {
        this.zone = zone;
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

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
    }
}
