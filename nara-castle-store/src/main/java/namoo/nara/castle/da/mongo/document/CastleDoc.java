package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "CA_CASTLE")
@CompoundIndexes({
        @CompoundIndex(name = "idx_castellan_email",
                unique = true,
                def = "{'nationId' : 1, 'castellan.emails.emails.email' : 1}")
})
public class CastleDoc {

    @Id
    private String id;

    private Castellan castellan;

    @Indexed
    private String nationId;
    private String originMetroId;
    private String originCivilianId;

    private Long builtTime;

    @Version
    private Long entityVersion;

    public CastleDoc() {

    }

    public static CastleDoc toDocument(Castle castle) {

        CastleDoc castleDoc = new CastleDoc();
        castleDoc.setId(castle.getId());
        castleDoc.setEntityVersion(castle.getEntityVersion());
        BeanUtils.copyProperties(castle, castleDoc);
        return castleDoc;
    }

    public static List<Castle> toDomains(List<CastleDoc> castleDocuments) {

        return castleDocuments.stream()
                .map(doc -> doc.toDomain())
                .collect(Collectors.toList());
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

    public Castellan getCastellan() {
        return castellan;
    }

    public void setCastellan(Castellan castellan) {
        this.castellan = castellan;
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
    }

    public String getOriginMetroId() {
        return originMetroId;
    }

    public void setOriginMetroId(String originMetroId) {
        this.originMetroId = originMetroId;
    }

    public String getOriginCivilianId() {
        return originCivilianId;
    }

    public void setOriginCivilianId(String originCivilianId) {
        this.originCivilianId = originCivilianId;
    }

    public Long getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(Long builtTime) {
        this.builtTime = builtTime;
    }

    public Long getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(Long entityVersion) {
        this.entityVersion = entityVersion;
    }
}
