package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.Castle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Document(collection = "Castle")
public class CastleDoc {
    //
    @Id
    private String id;

    private CastellanDoc castellan;

    private Locale locale;
    private Instant builtTimeUTC;
    private String zoneId;

    @Version
    private Long version;

    public CastleDoc() {
        //
    }

    public static CastleDoc toDocument(Castle castle) {
        //
        CastleDoc castleDoc = new CastleDoc();
        castleDoc.setId(castle.getId());
        castleDoc.setCastellan(CastellanDoc.toDocument(castle.getCastellan()));
        castleDoc.setLocale(castle.getLocale());
        castleDoc.setBuiltTimeUTC(castle.getBuiltTime().toInstant());
        castleDoc.setZoneId(castle.getBuiltTime().getZone().getId());
        castleDoc.setVersion(castle.getVersion());
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
        Castle castle = new Castle(id);
        castle.setCastellan(castellan.toDomain());
        castle.setLocale(locale);
        castle.setBuiltTime(ZonedDateTime.ofInstant(builtTimeUTC, ZoneId.of(zoneId)));
        castle.setVersion(version);
        return castle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CastellanDoc getCastellan() {
        return castellan;
    }

    public void setCastellan(CastellanDoc castellan) {
        this.castellan = castellan;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Instant getBuiltTimeUTC() {
        return builtTimeUTC;
    }

    public void setBuiltTimeUTC(Instant builtTimeUTC) {
        this.builtTimeUTC = builtTimeUTC;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
