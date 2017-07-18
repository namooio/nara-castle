package nara.castle.domain.view;

import nara.castle.domain.entity.Castellan;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.granule.AddressList;
import namoo.nara.share.domain.granule.EmailList;
import namoo.nara.share.domain.granule.NameList;
import namoo.nara.share.domain.granule.PhoneList;

public class CastellanView {
    //
    private String id;

    private NameList names;
    private PhoneList phones;
    private EmailList emails;
    private AddressList addresses;
    private NameValueList attrNameValues;

    public CastellanView() {
        //
    }

    public CastellanView(Castellan castellan) {
        //
        this.id = castellan.getId();
        this.names = castellan.getNames();
        this.phones = castellan.getPhones();
        this.emails = castellan.getEmails();
        this.addresses = castellan.getAddresses();
        this.attrNameValues = castellan.getAttrNameValues();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("id:'").append(id).append('\'');
        sb.append(", names:").append(names);
        sb.append(", phones:").append(phones);
        sb.append(", emails:").append(emails);
        sb.append(", addresses:").append(addresses);
        sb.append(", attrNameValues:").append(attrNameValues);
        sb.append('}');
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
