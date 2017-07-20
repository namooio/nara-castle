package nara.castle.domain.castlequery.model;


import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.entity.Contact;
import nara.share.domain.Entity;
import nara.share.domain.granule.Email;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.Phone;
import nara.share.util.json.JsonUtil;

public class UnitPlateRM extends Entity {
    //
    private KeyAttr keyAttr;
    private String keyValue;
    private String castellanId;

    public UnitPlateRM() {
        //
    }

    public UnitPlateRM(String id) {
        //
        super(id);
    }

    public UnitPlateRM(String castellanId, Name name) {
        //
        super();
        this.keyValue = name.getDisplayName();
        if (name.isEngName()) {
            this.keyAttr = KeyAttr.EngName;
        } else {
            this.keyAttr = KeyAttr.LocaleName;
        }
        this.castellanId = castellanId;
    }

    public UnitPlateRM(String castellanId, Email email) {
        //
        super();
        this.keyAttr = KeyAttr.Email;
        this.keyValue = email.getEmail();
        this.castellanId = castellanId;
    }

    public UnitPlateRM(String castellanId, Phone phone) {
        //
        super();
        this.keyAttr = KeyAttr.Email;
        this.keyValue = phone.getCarrierFullNumber();
        this.castellanId = castellanId;
    }

    public static UnitPlateList extractUnitPlates(String castellanId, Contact contact) {
        //
        UnitPlateList unitPlates = new UnitPlateList();

        for(Name name: contact.getNames().getNames()) {
            unitPlates.add(new UnitPlateRM(castellanId, name));
        }

        for(Phone phone: contact.getPhones().getPhones()) {
            unitPlates.add(new UnitPlateRM(castellanId, phone));
        }

        for(Email email : contact.getEmails().getEmails()) {
            unitPlates.add(new UnitPlateRM(castellanId, email));
        }

        return unitPlates;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnitPlateRM{");
        sb.append("keyAttr=").append(keyAttr);
        sb.append(", keyValue='").append(keyValue).append('\'');
        sb.append(", castellanId='").append(castellanId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static UnitPlateRM getSample() {
        //
        String castellanId = Castellan.getSample().getId();
        Email email = Email.getSample();
        UnitPlateRM sample = new UnitPlateRM(castellanId, email);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static UnitPlateRM fromJson(String json) {
        //
        return JsonUtil.fromJson(json, UnitPlateRM.class);
    }

    public KeyAttr getKeyAttr() {
        return keyAttr;
    }

    public void setKeyAttr(KeyAttr keyAttr) {
        this.keyAttr = keyAttr;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}