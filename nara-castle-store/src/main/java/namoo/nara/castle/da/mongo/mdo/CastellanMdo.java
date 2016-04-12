package namoo.nara.castle.da.mongo.mdo;

import namoo.nara.castle.domain.entity.Castellan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Document(collection = "Castellan")
public class CastellanMdo {
    //
    @Id
    private String id;              // == castle id
    private String displayName;
    private String photoId;         // profile photo id
    private String primaryEmail;    // nullable
    private String primaryPhone;    // nullable

    public CastellanMdo() {
        //
    }

    public static CastellanMdo newInstance(Castellan castellan) {
        //
        CastellanMdo castellanMdo = new CastellanMdo();
        castellanMdo.setId(castellan.getId());
        castellanMdo.setDisplayName(castellan.getDisplayName());
        castellanMdo.setPhotoId(castellan.getPhotoId());
        castellanMdo.setPrimaryEmail(castellan.getPrimaryEmail());
        castellanMdo.setPrimaryPhone(castellan.getPrimaryPhone());
        return castellanMdo;
    }

    public Castellan toDomain() {
        //
        Castellan castle = Castellan.newInstance(id, displayName);
        castle.setPhotoId(photoId);
        castle.setPrimaryEmail(primaryEmail);
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
