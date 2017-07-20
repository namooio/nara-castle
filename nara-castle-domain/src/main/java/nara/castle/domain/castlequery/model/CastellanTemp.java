package nara.castle.domain.castlequery.model;

import nara.castle.domain.castle.entity.Castle;
import nara.castle.domain.castle.entity.Enrollment;
import nara.castle.domain.castle.event.CastellanModified;
import nara.share.domain.Entity;
import nara.share.domain.NameValue;
import nara.share.domain.NameValueList;
import nara.share.domain.event.NaraEvent;
import nara.share.domain.granule.*;
import nara.share.util.json.JsonUtil;

public class CastellanTemp extends Entity {
    //
    private String displayName;
    private String primaryEmail;

    transient private UnitPlateList unitPlates;     // strong association

    public CastellanTemp() {
        //
    }

    public CastellanTemp(String id) {
        //
        super(id);
    }

    public CastellanTemp(String castleId, Enrollment enrollment) {
        //
        super(castleId);
        this.names = new NameList(enrollment.getName());
        this.phones = new PhoneList();
        this.emails = new EmailList(new Email(enrollment.getEmail()));
        this.addresses = new AddressList();
        this.attrNameValues = new NameValueList();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Castellan{");
        sb.append("names=").append(names);
        sb.append(", phones=").append(phones);
        sb.append(", emails=").append(emails);
        sb.append(", addresses=").append(addresses);
        sb.append(", attrNameValues=").append(attrNameValues);
        sb.append(", unitPlates=").append(unitPlates);
        sb.append('}');
        return sb.toString();
    }

    public static CastellanTemp getSample() {
        //
        Castle castle = Castle.getSample();
        CastellanTemp sample = new CastellanTemp(castle.getId());

        return sample;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static CastellanTemp fromJson(String json) {
        return JsonUtil.fromJson(json, CastellanTemp.class);
    }

    public void apply(NaraEvent event) {
        //
        if (event instanceof CastellanCreated) {
            CastellanCreated castellanCreated = (CastellanCreated) event;

            CastellanTemp castellan = castellanCreated.getCastellan();

            this.names = castellan.getNames();
            this.phones = castellan.getPhones();
            this.emails = castellan.getEmails();
            this.attrNameValues = castellan.getAttrNameValues();
        }
        else if (event instanceof CastellanModified) {
            CastellanModified castellanModified = (CastellanModified) event;
            setValues(castellanModified.getNameValues());
        }
    }

    public void setValues(NameValueList nameValues) {
        //
        for(NameValue nameValue : nameValues.getList()) {
            String name = nameValue.getName();
            String value = nameValue.getValue();
            switch (name) {
                case "names":   this.names = NameList.fromJson(value); break;
                case "phones":   this.phones = PhoneList.fromJson(value); break;
                case "emails":   this.emails = EmailList.fromJson(value); break;
                case "attrNameValues":   this.attrNameValues = NameValueList.fromJson(value); break;
            }
        }

        this.initUnitPlates();
    }

    public void initUnitPlates() {
        //
        this.unitPlates = new UnitPlateList();

        for(Name name: names.getNames()) {
            unitPlates.add(new UnitPlate(getId(), name));
        }

        for(Phone phone: phones.getPhones()) {
            unitPlates.add(new UnitPlate(getId(), phone));
        }

        for(Email email : emails.getEmails()) {
            unitPlates.add(new UnitPlate(getId(), email));
        }
    }

    public boolean checkName(Name name) {
        //
        if (!names.contains(name.getFirstName(), name.getFamilyName())) {
            names.add(name);
            return true;
        }

        return false;
    }

    public boolean checkEmail(String email) {
        //
        if(!emails.contains(email)) {
            emails.add(new Email(email));
            return true;
        }

        return false;
    }

    public NameList getNames() {
        return names;
    }

    public void setNames(NameList names) {
        this.names = names;
    }

    public PhoneList getPhones() {
        return phones;
    }

    public void setPhones(PhoneList phones) {
        this.phones = phones;
    }

    public EmailList getEmails() {
        return emails;
    }

    public void setEmails(EmailList emails) {
        this.emails = emails;
    }

    public AddressList getAddresses() {
        return addresses;
    }

    public void setAddresses(AddressList addresses) {
        this.addresses = addresses;
    }

    public NameValueList getAttrNameValues() {
        return attrNameValues;
    }

    public void setAttrNameValues(NameValueList attrNameValues) {
        this.attrNameValues = attrNameValues;
    }

    public UnitPlateList getUnitPlates() {
        return unitPlates;
    }

    public void setUnitPlates(UnitPlateList unitPlates) {
        this.unitPlates = unitPlates;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }

}