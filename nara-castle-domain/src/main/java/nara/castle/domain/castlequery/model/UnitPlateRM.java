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
    private String key;
    private KeyAttr attr;
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
        this.key = name.getDisplayName();
        if (name.isEngName()) {
            this.attr = KeyAttr.EngName;
        } else {
            this.attr = KeyAttr.LocaleName;
        }
        this.castellanId = castellanId;
    }

    public UnitPlateRM(String castellanId, Email email) {
        //
        super();
        this.key = email.getEmail();
        this.attr = KeyAttr.Email;
        this.castellanId = castellanId;
    }

    public UnitPlateRM(String castellanId, Phone phone) {
        //
        super();
        this.key = phone.getCarrierFullNumber();
        this.attr = KeyAttr.Email;
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
        sb.append("key='").append(key).append('\'');
        sb.append(", attr=").append(attr);
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public KeyAttr getAttr() {
        return attr;
    }

    public void setAttr(KeyAttr attr) {
        this.attr = attr;
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