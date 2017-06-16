package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.Entity;
import namoo.nara.share.domain.NameValue;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.granule.*;
import namoo.nara.share.util.json.JsonUtil;

public class Castellan extends Entity {
    //
    private NameList names;
    private PhoneList phones;
    private EmailList emails;
    private AddressList addresses;
    private NameValueList attrNameValues;           // additional attributes

    public Castellan() {
        //
    }

    public Castellan(String id) {
        //
        super(id);
    }

    public Castellan(MetroEnrollment metroEnrollment) {
        //
        super(metroEnrollment.getCastleId());       // castleId == castellanId
        this.names = new NameList(metroEnrollment.getName());
        this.phones = new PhoneList();
        this.emails = new EmailList(new Email(metroEnrollment.getEmail()));
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
        sb.append('}');
        return sb.toString();
    }

    public static Castellan getSample() {
        //
        MetroEnrollment enrollment = MetroEnrollment.getSample();

        Castellan sample = new Castellan(enrollment);

        return sample;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Castellan fromJson(String json) {
        return JsonUtil.fromJson(json, Castellan.class);
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
    }

    public UnitPlateList requestUnitPlates() {
        //
        UnitPlateList unitPlates = new UnitPlateList();

        for(Name name: names.getNames()) {
            unitPlates.add(new UnitPlate(getId(), name));
        }

        for(Phone phone: phones.getPhones()) {
            unitPlates.add(new UnitPlate(getId(), phone));
        }

        for(Email email : emails.getEmails()) {
            unitPlates.add(new UnitPlate(getId(), email));
        }

        return unitPlates;
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

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}