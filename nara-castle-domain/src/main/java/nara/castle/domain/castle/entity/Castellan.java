package nara.castle.domain.castle.entity;

import nara.share.domain.Aggregate;
import nara.share.domain.Entity;
import nara.share.domain.NameValue;
import nara.share.domain.NameValueList;
import nara.share.domain.event.NaraEvent;
import nara.share.util.json.JsonUtil;

import java.util.List;

public class Castellan extends Entity implements Aggregate {
    //
    private String displayName;
    private String primaryEmail;

    private Contact contact;
    private List<Enrollment> enrollments;
    private Castle castle;                  // Nominal object

    public Castellan() {
        //
    }

    public Castellan(String id) {
        //
        super(id);
    }

    public Castellan(Enrollment enrollment) {
        //
        super();
        this.displayName = enrollment.getName().getDisplayName();
        this.primaryEmail = enrollment.getEmail().getEmail();
        this.castle = new Castle();
        this.contact = new Contact(enrollment);

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Castellan{");
        sb.append("displayName='").append(displayName).append('\'');
        sb.append(", primaryEmail='").append(primaryEmail).append('\'');
        sb.append(", contact=").append(contact);
        sb.append(", enrollments=").append(enrollments);
        sb.append(", castle=").append(castle);
        sb.append('}');
        return sb.toString();
    }

    public static Castellan getSample() {
        //
        Enrollment enrollment = Enrollment.getSample();
        Castellan sample = new Castellan(enrollment);

        return sample;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Castellan fromJson(String json) {
        return JsonUtil.fromJson(json, Castellan.class);
    }

    public void apply(NaraEvent event) {
        //
//        if (event instanceof CastellanCreated) {
//            CastellanCreated castellanCreated = (CastellanCreated) event;
//
//            Castellan castellan = castellanCreated.getCastellan();
//
//            this.names = castellan.getNames();
//            this.phones = castellan.getPhones();
//            this.emails = castellan.getEmails();
//            this.attrNameValues = castellan.getAttrNameValues();
//        }
//        else if (event instanceof CastellanModified) {
//            CastellanModified castellanModified = (CastellanModified) event;
//            setValues(castellanModified.getNameValues());
//        }
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

    public void addEnrollment(Enrollment enrollment) {
        //
        this.enrollments.add(enrollment);
        this.contact.addFromEnrollment(enrollment);
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

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}