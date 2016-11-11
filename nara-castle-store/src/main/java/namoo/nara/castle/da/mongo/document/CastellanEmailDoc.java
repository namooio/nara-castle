package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.CastellanEmail;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CastellanEmailDoc {
    //
    @Indexed(unique = true)
    private String address;
    private Instant createdTimeUTC;
    private String createdTimeZoneId;

    public CastellanEmailDoc() {
        //
    }

    public static CastellanEmailDoc toDocument(CastellanEmail domain) {
        //
        if (domain == null) return null;
        CastellanEmailDoc doc = new CastellanEmailDoc();
        doc.setAddress(domain.getAddress());
        doc.setCreatedTimeUTC(domain.getCreatedTime().toInstant());
        doc.setCreatedTimeZoneId(domain.getCreatedTime().getZone().getId());
        return doc;
    }

    public static Set<CastellanEmailDoc> toDocuments(Set<CastellanEmail> domains) {
        //
        if (domains == null) return null;
        return domains.stream()
                    .filter(Objects::nonNull)
                    .map(CastellanEmailDoc::toDocument)
                    .collect(Collectors.toSet());
    }

    public CastellanEmail toDomain() {
        //
        CastellanEmail domain = new CastellanEmail(address);
        domain.setCreatedTime(ZonedDateTime.ofInstant(createdTimeUTC, ZoneId.of(createdTimeZoneId)));
        return domain;
    }

    public static Set<CastellanEmail> toDomains(Set<CastellanEmailDoc> docs) {
        //
        if (docs == null) return null;
        return docs.stream()
                .filter(Objects::nonNull)
                .map(doc -> doc.toDomain())
                .collect(Collectors.toSet());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Instant getCreatedTimeUTC() {
        return createdTimeUTC;
    }

    public void setCreatedTimeUTC(Instant createdTimeUTC) {
        this.createdTimeUTC = createdTimeUTC;
    }

    public String getCreatedTimeZoneId() {
        return createdTimeZoneId;
    }

    public void setCreatedTimeZoneId(String createdTimeZoneId) {
        this.createdTimeZoneId = createdTimeZoneId;
    }

}
