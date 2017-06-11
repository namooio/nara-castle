package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.Entity;
import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.domain.granule.Phone;
import namoo.nara.share.util.json.JsonUtil;

public class IdentityPlate extends Entity {
    //
    private String primaryEmail;
    private String secondaryEmail;
    private String mobilePhone;         // mobile -> carrier+frontNumber+backNumber only, 01091152020
    private String officePhone;         // phone -> same, 0223440909
    private String firstNameInLocale;   // 태훈
    private String familyNameInLocale;  // 송
    private String displayNameInLocale; // 송태훈
    private String firstNameInEng;      // Taegook
    private String familyNameInEng;     // Song
    private String displayNameInEng;    // Taegook Song

    public IdentityPlate(String id) {
        //
        super(id);
    }

    public IdentityPlate(Castellan castellan) {
        //
        super(castellan.getId());               // castleId == castellanId
        this.setPrimaryEmail(castellan);
        this.setSecondaryEmail(castellan);
        this.setMobilePhone(castellan);
        this.setOfficePhone(castellan);
        this.setEngName(castellan);
        this.setLocaledName(castellan);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IdentityPlate{");
        sb.append("primaryEmail='").append(primaryEmail).append('\'');
        sb.append(", secondaryEmail='").append(secondaryEmail).append('\'');
        sb.append(", mobilePhone='").append(mobilePhone).append('\'');
        sb.append(", officePhone='").append(officePhone).append('\'');
        sb.append(", firstNameInLocale='").append(firstNameInLocale).append('\'');
        sb.append(", familyNameInLocale='").append(familyNameInLocale).append('\'');
        sb.append(", displayNameInLocale='").append(displayNameInLocale).append('\'');
        sb.append(", firstNameInEng='").append(firstNameInEng).append('\'');
        sb.append(", familyNameInEng='").append(familyNameInEng).append('\'');
        sb.append(", displayNameInEng='").append(displayNameInEng).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static IdentityPlate getSample() {
        //
        Castellan castellan = Castellan.getSample();

        IdentityPlate sample = new IdentityPlate(castellan);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static IdentityPlate fromJson(String json) {
        //
        return JsonUtil.fromJson(json, IdentityPlate.class);
    }

    public void setEngName(Castellan castellan) {
        //
        for(Name name : castellan.getNames().getNames()) {
            //
            if(name.isEngName()) {
                this.firstNameInEng = name.getFirstName();
                this.familyNameInEng = name.getFamilyName();
                this.displayNameInEng = name.getDisplayName();
                break;
            }
        }
    }

    public void setLocaledName(Castellan castellan) {
        //
        for(Name name : castellan.getNames().getNames()) {
            if(!name.isEngName()) {
                this.firstNameInLocale = name.getFirstName();
                this.familyNameInLocale = name.getFamilyName();
                this.displayNameInLocale = name.getDisplayName();
                break;
            }
        }
    }

    public void setMobilePhone(Castellan castellan) {
        //
        for(Phone phone : castellan.getPhones().getPhones()) {
            if (phone.getCategory().equals(Phone.Category.Mobile)) {
                this.mobilePhone = phone.getCarrierFullNumber();
                break;
            }
        }
    }

    public void setOfficePhone(Castellan castellan) {
        //
        for(Phone phone : castellan.getPhones().getPhones()) {
            if (phone.getCategory().equals(Phone.Category.Office)) {
                this.officePhone = phone.getCarrierFullNumber();
                break;
            }
        }
    }

    public void setPrimaryEmail(Castellan castellan) {
        //
        if (castellan.getEmails().hasPrimary()) {
            this.primaryEmail = castellan.getEmails().getFirstPrimary().getEmail();
        }
    }

    public void setSecondaryEmail(Castellan castellan) {
        //
        if(castellan.getEmails().hasSecondary()) {
            this.secondaryEmail = castellan.getEmails().getFirstSecondary().getEmail();
        }
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getFirstNameInLocale() {
        return firstNameInLocale;
    }

    public void setFirstNameInLocale(String firstNameInLocale) {
        this.firstNameInLocale = firstNameInLocale;
    }

    public String getFamilyNameInLocale() {
        return familyNameInLocale;
    }

    public void setFamilyNameInLocale(String familyNameInLocale) {
        this.familyNameInLocale = familyNameInLocale;
    }

    public String getDisplayNameInLocale() {
        return displayNameInLocale;
    }

    public void setDisplayNameInLocale(String displayNameInLocale) {
        this.displayNameInLocale = displayNameInLocale;
    }

    public String getFirstNameInEng() {
        return firstNameInEng;
    }

    public void setFirstNameInEng(String firstNameInEng) {
        this.firstNameInEng = firstNameInEng;
    }

    public String getFamilyNameInEng() {
        return familyNameInEng;
    }

    public void setFamilyNameInEng(String familyNameInEng) {
        this.familyNameInEng = familyNameInEng;
    }

    public String getDisplayNameInEng() {
        return displayNameInEng;
    }

    public void setDisplayNameInEng(String displayNameInEng) {
        this.displayNameInEng = displayNameInEng;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}