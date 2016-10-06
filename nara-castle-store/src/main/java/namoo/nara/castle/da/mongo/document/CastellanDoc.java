package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.Castellan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "Castellan")
@CompoundIndexes({
        @CompoundIndex(name = "idx_castellan_account",
                sparse = true,
                unique = true,
                def = "{'accounts.key' : 1}")
//                def = "{'accounts.loginId' : 1, 'accounts.loginIdType' : 1}")
})
public class CastellanDoc {
    //
    @Id
    private String id;              // == castle id
    private String name;
    private String photoId;         // profile photo id

    private Set<LoginAccountDoc> accounts;
    private LoginCredentialDoc credential;

    private Set<CastellanEmailDoc> emails;
    private List<JoinedMetroDoc> joinedMetros;

    private Instant createdTimeUTC;
    private String zoneId;


    public CastellanDoc() {
        //
    }

    public static CastellanDoc toDocument(Castellan castellan) {
        //
        CastellanDoc castellanDoc = new CastellanDoc();
        castellanDoc.setId(castellan.getId());
        castellanDoc.setName(castellan.getName());
        castellanDoc.setPhotoId(castellan.getPhotoId());

        castellanDoc.setAccounts(LoginAccountDoc.toDocuments(castellan.getAccounts()));
        castellanDoc.setCredential(LoginCredentialDoc.toDocument(castellan.getCredential()));

        castellanDoc.setEmails(CastellanEmailDoc.toDocuments(castellan.getEmails()));
        castellanDoc.setJoinedMetros(JoinedMetroDoc.toDocuments(castellan.getJoinedMetros()));

        castellanDoc.setCreatedTimeUTC(castellan.getCreatedTime().toInstant());
        castellanDoc.setZoneId(castellan.getCreatedTime().getZone().getId());

        return castellanDoc;
    }

    public Castellan toDomain() {
        //
        Castellan castellan = new Castellan(id);
        castellan.setName(name);
        castellan.setPhotoId(photoId);

        castellan.setAccounts(LoginAccountDoc.toDomains(accounts));
        if (credential != null) castellan.setCredential(credential.toDomain());

        castellan.setEmails(CastellanEmailDoc.toDomains(emails));
        castellan.setJoinedMetros(JoinedMetroDoc.toDomains(joinedMetros));

        castellan.setCreatedTime(ZonedDateTime.ofInstant(createdTimeUTC, ZoneId.of(zoneId)));
        return castellan;
    }

    public static List<Castellan> toDomains(List<CastellanDoc> castellanDocuments) {
        //
        if (castellanDocuments == null) return null;
        return castellanDocuments.stream()
                            .filter(Objects::nonNull)
                            .map(doc -> doc.toDomain())
                            .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Set<LoginAccountDoc> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<LoginAccountDoc> accounts) {
        this.accounts = accounts;
    }

    public LoginCredentialDoc getCredential() {
        return credential;
    }

    public void setCredential(LoginCredentialDoc credential) {
        this.credential = credential;
    }

    public Set<CastellanEmailDoc> getEmails() {
        return emails;
    }

    public void setEmails(Set<CastellanEmailDoc> emails) {
        this.emails = emails;
    }

    public List<JoinedMetroDoc> getJoinedMetros() {
        return joinedMetros;
    }

    public void setJoinedMetros(List<JoinedMetroDoc> joinedMetros) {
        this.joinedMetros = joinedMetros;
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
