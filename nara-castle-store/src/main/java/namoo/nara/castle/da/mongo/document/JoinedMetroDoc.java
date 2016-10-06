package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.JoinedMetro;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JoinedMetroDoc {

    private String metroId;
    private String citizenId;

    private Instant joinedTimeUTC;
    private String zoneId;

    public JoinedMetroDoc() {
        //
    }

    public static JoinedMetroDoc toDocument(JoinedMetro domain) {
        //
        if (domain == null) return null;
        JoinedMetroDoc doc = new JoinedMetroDoc();
        doc.setMetroId(domain.getMetroId());
        doc.setCitizenId(domain.getCitizenId());
        doc.setJoinedTimeUTC(domain.getJoinedTime().toInstant());
        doc.setZoneId(domain.getJoinedTime().getZone().getId());
        return doc;
    }

    public static List<JoinedMetroDoc> toDocuments(List<JoinedMetro> domains) {
        //
        if (domains == null) return null;
        return domains.stream()
                    .filter(Objects::nonNull)
                    .map(JoinedMetroDoc::toDocument)
                    .collect(Collectors.toList());
    }

    public JoinedMetro toDomain() {
        //
        JoinedMetro domain = new JoinedMetro();
        domain.setMetroId(metroId);
        domain.setCitizenId(citizenId);
        domain.setJoinedTime(ZonedDateTime.ofInstant(joinedTimeUTC, ZoneId.of(zoneId)));
        return domain;
    }

    public static List<JoinedMetro> toDomains(List<JoinedMetroDoc> docs) {
        //
        if (docs == null) return null;
        return docs.stream()
                .filter(Objects::nonNull)
                .map(doc -> doc.toDomain())
                .collect(Collectors.toList());
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

    public Instant getJoinedTimeUTC() {
        return joinedTimeUTC;
    }

    public void setJoinedTimeUTC(Instant joinedTimeUTC) {
        this.joinedTimeUTC = joinedTimeUTC;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }
}
