package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.Castellan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Document(collection = "Castellan")
public class CastellanDoc {
    //
    @Id
    private String id;              // == castle id
    private String displayName;
    private String photoId;         // profile photo id
    private String primaryEmail;    // nullable
    private String primaryPhone;    // nullable

    public CastellanDoc() {
        //
    }

    public static CastellanDoc newInstance(Castellan castellan) {
        //
        CastellanDoc castellanDoc = new CastellanDoc();
        castellanDoc.setId(castellan.getId());
        castellanDoc.setDisplayName(castellan.getDisplayName());
        castellanDoc.setPhotoId(castellan.getPhotoId());
        castellanDoc.setPrimaryEmail(castellan.getPrimaryEmail());
        castellanDoc.setPrimaryPhone(castellan.getPrimaryPhone());
        return castellanDoc;
    }

    public Castellan toDomain() {
        //
        Castellan castle = Castellan.newInstance(id, displayName, primaryEmail);
        castle.setPhotoId(photoId);
        castle.setPrimaryPhone(primaryPhone);
        return castle;
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

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }
}
