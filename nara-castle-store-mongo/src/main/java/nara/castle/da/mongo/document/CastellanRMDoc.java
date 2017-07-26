package nara.castle.da.mongo.document;

import nara.castle.domain.castle.entity.Contact;
import nara.castle.domain.castlequery.model.CastellanRM;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Entity("CA_CASTELLAN")
public class CastellanRMDoc {
    //
    @Id
    private String id;

    private String displayName;
    private String primaryEmail;
    private Contact contact;
    private Long castleBuiltTime;

    public CastellanRMDoc() {
        //
    }

    public static CastellanRMDoc toDocument(CastellanRM castellanRM) {
        //
        CastellanRMDoc castellanRMDoc = new CastellanRMDoc();
        BeanUtils.copyProperties(castellanRM, castellanRMDoc);
        return castellanRMDoc;
    }

    public static List<CastellanRM> toModel(List<CastellanRMDoc> castellanRMDocs) {
        //
        return castellanRMDocs.stream().map(doc -> doc.toModel()).collect(Collectors.toList());
    }

    public CastellanRM toModel() {
        //
        CastellanRM castellanRM = new CastellanRM(id);
        BeanUtils.copyProperties(this, castellanRM);
        return castellanRM;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Long getCastleBuiltTime() {
        return castleBuiltTime;
    }

    public void setCastleBuiltTime(Long castleBuiltTime) {
        this.castleBuiltTime = castleBuiltTime;
    }
}
