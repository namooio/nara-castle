package namoo.nara.castle.da.jpa.jpa.jpo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
@Entity(name = "NARA_CASTELLAN_NAME")
public class CastellanNameJpo {

    /**
     * Jpa entity id
     */
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String castellanOid;

    @Column
    private String givenName;

    @Column
    private String familyName;

    @Column
    private String middleName;

    @Column
    private String langCode;

    @Column
    private String displayName;

    @Column(nullable = false)
    private boolean primaryName;

    public CastellanNameJpo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCastellanOid() {
        return castellanOid;
    }

    public void setCastellanOid(String castellanOid) {
        this.castellanOid = castellanOid;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(boolean primaryName) {
        this.primaryName = primaryName;
    }
}
