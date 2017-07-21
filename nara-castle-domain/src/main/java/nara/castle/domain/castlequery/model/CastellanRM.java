package nara.castle.domain.castlequery.model;

import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.entity.Contact;
import nara.share.domain.Entity;
import nara.share.domain.NameValue;
import nara.share.domain.NameValueList;
import nara.share.util.json.JsonUtil;

public class CastellanRM extends Entity {
    //
    private String displayName;
    private String primaryEmail;
    private Contact contact;

    public CastellanRM() {
        //
    }

    public CastellanRM(String id) {
        //
        super(id);
    }

    public CastellanRM(Castellan castellan) {
        //
        super(castellan.getId());
        this.displayName = castellan.getDisplayName();
        this.primaryEmail = castellan.getPrimaryEmail();
        this.contact = castellan.getContact();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CastellanRM{");
        sb.append("displayName='").append(displayName).append('\'');
        sb.append(", primaryEmail='").append(primaryEmail).append('\'');
        sb.append(", contact=").append(contact);
        sb.append('}');
        return sb.toString();
    }

    public static CastellanRM getSample() {
        //
        Castellan castellan = Castellan.getSample();
        CastellanRM sample = new CastellanRM(castellan);

        return sample;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static CastellanRM fromJson(String json) {
        return JsonUtil.fromJson(json, CastellanRM.class);
    }

    public void setValues(NameValueList nameValues) {
        //
        for(NameValue nameValue : nameValues.getList()) {
            String name = nameValue.getName();
            String value = nameValue.getValue();
            switch (name) {
                case "displayName":     this.displayName = value; break;
                case "primaryEmail":    this.primaryEmail = value; break;
                case "contact":         this.contact = Contact.fromJson(value); break;
            }
        }
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

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}