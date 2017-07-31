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

    // required for paging or soring
    private Long castleBuiltTime;

    public UnitPlateRM() {
        //
    }

    public UnitPlateRM(String id) {
        //
        super(id);
    }

    public UnitPlateRM(String castellanId, Long castleBuiltTime, Name name) {
        //
        super();
        this.keyValue = name.getDisplayName();
        if (name.isEngName()) {
            this.keyAttr = KeyAttr.EngName;
        } else {
            this.keyAttr = KeyAttr.LocaleName;
        }
        this.castellanId = castellanId;
        this.castleBuiltTime = castleBuiltTime;
    }

    public UnitPlateRM(String castellanId, Long castleBuiltTime, Email email) {
        //
        super();
        this.keyAttr = KeyAttr.Email;
        this.keyValue = email.getEmail();
        this.castellanId = castellanId;
        this.castleBuiltTime = castleBuiltTime;
    }

    public UnitPlateRM(String castellanId, Long castleBuiltTime, Phone phone) {
        //
        super();
        this.keyAttr = KeyAttr.Phone;
        this.keyValue = phone.getCarrierFullNumber();
        this.castellanId = castellanId;
        this.castleBuiltTime = castleBuiltTime;
    }

    public static UnitPlateList extractUnitPlates(CastellanRM castellanRM) {
        //
        UnitPlateList unitPlates = new UnitPlateList();

        String castellanId = castellanRM.getId();
        Long castleBuiltTime = castellanRM.getCastleBuiltTime();

        Contact contact = castellanRM.getContact();

        for(Name name: contact.getNames().getNames()) {
            unitPlates.add(new UnitPlateRM(castellanId, castleBuiltTime, name));
        }

        for(Phone phone: contact.getPhones().getPhones()) {
            unitPlates.add(new UnitPlateRM(castellanId, castleBuiltTime, phone));
        }

        for(Email email : contact.getEmails().getEmails()) {
            unitPlates.add(new UnitPlateRM(castellanId, castleBuiltTime, email));
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
        Castellan castellan = Castellan.getSample();
        String castellanId = castellan.getId();
        Long castleBuiltTime = castellan.getCastle().getBuiltTime();
        Email email = Email.getSample();
        UnitPlateRM sample = new UnitPlateRM(castellanId, castleBuiltTime, email);

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

    public Long getCastleBuiltTime() {
        return castleBuiltTime;
    }

    public void setCastleBuiltTime(Long castleBuiltTime) {
        this.castleBuiltTime = castleBuiltTime;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}