package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.Castellan;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CastellanDoc {
    //
    private Set<CastellanEmailDoc> emails;
    private List<JoinedMetroDoc> joinedMetros;

    private String zoneId;


    public CastellanDoc() {
        //
    }

    public static CastellanDoc toDocument(Castellan castellan) {
        //
        CastellanDoc castellanDoc = new CastellanDoc();

        castellanDoc.setEmails(CastellanEmailDoc.toDocuments(castellan.getEmails()));
        castellanDoc.setJoinedMetros(JoinedMetroDoc.toDocuments(castellan.getJoinedMetros()));

        return castellanDoc;
    }

    public Castellan toDomain() {
        //
        Castellan castellan = new Castellan();

        castellan.setEmails(CastellanEmailDoc.toDomains(emails));
        castellan.setJoinedMetros(JoinedMetroDoc.toDomains(joinedMetros));

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

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }
}
