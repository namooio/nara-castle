package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Document(collection = "CA_CASTLE")
public class CastleDoc {
    //
    @Id
    private String id;

    private Castellan castellan;

    private Locale locale;
    private Instant builtTime;

    private String originMetroId;
    private String originCitizenId;

    @Version
    private Long entityVersion;

    public CastleDoc() {
        //
    }

    public static CastleDoc toDocument(Castle castle) {
        //
        CastleDoc castleDoc = new CastleDoc();
        castleDoc.setId(castle.getId());
        castleDoc.setCastellan(castle.getCastellan());
        castleDoc.setBuiltTime(castle.getBuiltTime());
        castleDoc.setLocale(castle.getLocale());
        castleDoc.setOriginMetroId(castle.getOriginMetroId());
        castleDoc.setOriginCitizenId(castle.getOriginCitizenId());
        castleDoc.setEntityVersion(castle.getEntityVersion());
        return castleDoc;
    }

    public static List<Castle> toDomains(List<CastleDoc> castleDocuments) {
        //
        return castleDocuments.stream()
                .map(doc -> doc.toDomain())
                .collect(Collectors.toList());
    }

    public Castle toDomain() {
        //
        Castle castle = new Castle(id, castellan, originMetroId, originCitizenId, builtTime);
        castle.setLocale(locale);
        castle.setEntityVersion(entityVersion);
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

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Instant getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(Instant builtTime) {
        this.builtTime = builtTime;
    }

    public String getOriginMetroId() {
        return originMetroId;
    }

    public void setOriginMetroId(String originMetroId) {
        this.originMetroId = originMetroId;
    }

    public String getOriginCitizenId() {
        return originCitizenId;
    }

    public void setOriginCitizenId(String originCitizenId) {
        this.originCitizenId = originCitizenId;
    }

    public Long getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(Long entityVersion) {
        this.entityVersion = entityVersion;
    }
}
