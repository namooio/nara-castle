package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.LoginAccount;
import namoo.nara.castle.domain.entity.LoginIdType;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class LoginAccountDoc {

    private AccountKeyDoc key;

    private Instant createdTimeUTC;
    private String zoneId;

    public LoginAccountDoc() {
        //
    }

    public static LoginAccountDoc toDocument(LoginAccount domain) {
        //
        if (domain == null) return null;
        LoginAccountDoc doc = new LoginAccountDoc();
        doc.setKey(AccountKeyDoc.newInstance(domain.getLoginId(), domain.getLoginIdType().name()));
        doc.setCreatedTimeUTC(domain.getCreatedTime().toInstant());
        doc.setZoneId(domain.getCreatedTime().getZone().getId());
        return doc;
    }

    public static Set<LoginAccountDoc> toDocuments(Set<LoginAccount> domains) {
        //
        if (domains == null) return null;
        return domains.stream()
                .filter(Objects::nonNull)
                .map(LoginAccountDoc::toDocument)
                .collect(Collectors.toSet());
    }

    public LoginAccount toDomain() {
        //
        LoginAccount domain = new LoginAccount();
        domain.setLoginId(key.getLoginId());
        domain.setLoginIdType(LoginIdType.valueOf(key.getLoginIdType()));
        domain.setCreatedTime(ZonedDateTime.ofInstant(createdTimeUTC, ZoneId.of(zoneId)));
        return domain;
    }

    public static Set<LoginAccount> toDomains(Set<LoginAccountDoc> docs) {
        //
        if (docs == null) return null;
        return docs.stream()
                .filter(Objects::nonNull)
                .map(doc -> doc.toDomain())
                .collect(Collectors.toSet());
    }

    public AccountKeyDoc getKey() {
        return key;
    }

    public void setKey(AccountKeyDoc key) {
        this.key = key;
    }

    public Instant getCreatedTimeUTC() {
        return createdTimeUTC;
    }

    public void setCreatedTimeUTC(Instant createdTimeUTC) {
        this.createdTimeUTC = createdTimeUTC;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }
}
