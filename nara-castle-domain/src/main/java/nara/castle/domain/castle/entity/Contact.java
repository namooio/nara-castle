package nara.castle.domain.castle.entity;

import nara.share.domain.NameValue;
import nara.share.domain.NameValueList;
import nara.share.domain.ValueObject;
import nara.share.domain.granule.AddressList;
import nara.share.domain.granule.EmailList;
import nara.share.domain.granule.NameList;
import nara.share.domain.granule.PhoneList;
import nara.share.util.json.JsonUtil;

public class Contact implements ValueObject {
    //
    private NameList names;
    private PhoneList phones;
    private EmailList emails;
    private AddressList addresses;

    public Contact() {
        //
    }

    public Contact(Enrollment enrollment) {
        //
        this.names = new NameList(enrollment.getName());
        this.phones = new PhoneList();
        this.emails = new EmailList(enrollment.getEmail());
        this.addresses = new AddressList();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contact{");
        sb.append("names=").append(names);
        sb.append(", phones=").append(phones);
        sb.append(", emails=").append(emails);
        sb.append(", addresses=").append(addresses);
        sb.append('}');
        return sb.toString();
    }

    public static Contact getSample() {
        //
        Enrollment enrollment = Enrollment.getSample();
        Contact sample = new Contact(enrollment);

        return sample;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Contact fromJson(String json) {
        return JsonUtil.fromJson(json, Contact.class);
    }

    public void setValues(NameValueList nameValues) {
        //
        for(NameValue nameValue : nameValues.getList()) {
            String name = nameValue.getName();
            String value = nameValue.getValue();
            switch (name) {
                case "names":       this.names = NameList.fromJson(value); break;
                case "phones":      this.phones = PhoneList.fromJson(value); break;
                case "emails":      this.emails = EmailList.fromJson(value); break;
                case "addresses":   this.addresses = AddressList.fromJson(value); break;
            }
        }
    }

    public void addFromEnrollment(Enrollment enrollment) {
        //
        this.names.add(enrollment.getName());
        this.emails.add(enrollment.getEmail());
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

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}