package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.share.domain.granule.NaraZone;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "CA_CASTLE")
@CompoundIndexes({
        @CompoundIndex(name = "idx_enrollments",
                unique = true,
                def = "{'enrollments.metroId' : 1, 'enrollments.civilianId' : 1}")
})
public class CastleDoc {
    //
    @Id
    private String id;

    private String startNationId;
    private String name;
    private String primaryEmail;
    private NaraZone zone;
    private Long builtTime;

    private List<MetroEnrollment> enrollments;       // weak association

    private Long entityVersion;

    public CastleDoc() {
        //
    }

    public static CastleDoc toDocument(Castle castle) {

        CastleDoc castleDoc = new CastleDoc();
        castleDoc.setId(castle.getId());
        castleDoc.setEntityVersion(castle.getEntityVersion());
        BeanUtils.copyProperties(castle, castleDoc);
        return castleDoc;
    }

    public static List<Castle> toDomains(List<CastleDoc> castleDocuments) {

        return castleDocuments.stream().map(doc -> doc.toDomain()).collect(Collectors.toList());
    }

    public Castle toDomain() {

        Castle castle = new Castle(id);
        castle.setEntityVersion(entityVersion);
        BeanUtils.copyProperties(this, castle);
        return castle;
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

    public Long getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(Long entityVersion) {
        this.entityVersion = entityVersion;
    }
}
