package nara.castle.da.mongo.document;

import nara.castle.domain.entity.MetroEnrollment;
import nara.castle.domain.view.CastleView;
import nara.share.domain.granule.NaraZone;
import org.mongodb.morphia.annotations.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Entity("CA_CASTLE")
@Indexes(
        @Index(
                value = "idx_enrollments",
                fields = {
                    @Field("enrollments.metroId"),
                    @Field("enrollments.civilianId")
                },
                unique = true
        )
)
public class CastleViewDoc {
    //
    @Id
    private String id;

    private String startNationId;
    private String name;
    private String primaryEmail;
    private NaraZone zone;
    private Long builtTime;

    private List<MetroEnrollment> enrollments;

    public CastleViewDoc() {
        //
    }

    public static CastleViewDoc toDocument(CastleView castleView) {
        //
        CastleViewDoc castleViewDoc = new CastleViewDoc();
        BeanUtils.copyProperties(castleView, castleViewDoc);
        return castleViewDoc;
    }

    public static List<CastleView> toDomains(List<CastleViewDoc> castleViewDocuments) {
        //
        return castleViewDocuments.stream().map(doc -> doc.toDomain()).collect(Collectors.toList());
    }

    public CastleView toDomain() {
        //
        CastleView castleView = new CastleView();
        BeanUtils.copyProperties(this, castleView);
        return castleView;
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
