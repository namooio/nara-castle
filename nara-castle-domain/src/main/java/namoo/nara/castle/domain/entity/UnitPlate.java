package namoo.nara.castle.domain.entity;


import namoo.nara.share.domain.Entity;
import namoo.nara.share.domain.granule.Email;
import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.domain.granule.Phone;
import namoo.nara.share.util.json.JsonUtil;

public class UnitPlate extends Entity {
    //
    private String key;
    private KeyAttr attr;
    private String castleId;

    public UnitPlate() {
        //
    }

    public UnitPlate(String id) {
        //
        super(id);
    }

    public UnitPlate(String castleId, Name name) {
        //
        super();
        this.key = name.getDisplayName();
        if (name.isEngName()) {
            this.attr = KeyAttr.EngName;
        } else {
            this.attr = KeyAttr.LocaleName;
        }
        this.castleId = castleId;
    }

    public UnitPlate(String castleId, Email email) {
        //
        super();
        this.key = email.getEmail();
        this.attr = KeyAttr.Email;
        this.castleId = castleId;
    }

    public UnitPlate(String castleId, Phone phone) {
        //
        super();
        this.key = phone.getCarrierFullNumber();
        this.attr = KeyAttr.Email;
        this.castleId = castleId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnitPlate{");
        sb.append("key='").append(key).append('\'');
        sb.append(", attr=").append(attr);
        sb.append(", castleId='").append(castleId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static UnitPlate getSample() {
        //
        String castleId = Castle.getSample().getId();
        Email email = Email.getSample();
        UnitPlate sample = new UnitPlate(castleId, email);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static UnitPlate fromJson(String json) {
        //
        return JsonUtil.fromJson(json, UnitPlate.class);
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

    public String getCastleId() {
        return castleId;
    }

    public void setCastleId(String castleId) {
        this.castleId = castleId;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}