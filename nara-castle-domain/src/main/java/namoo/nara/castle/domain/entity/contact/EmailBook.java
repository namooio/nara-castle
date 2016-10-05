package namoo.nara.castle.domain.entity.contact;

import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class EmailBook extends Entity implements Aggregate {
    //
    private Set<CastellanEmail> emails;

    public EmailBook(String id) {
        super(id);
    }

    public static EmailBook newInstance(String id) {
        //
        EmailBook emailBook = new EmailBook(id);
        emailBook.setEmails(new HashSet<>());
        return emailBook;
    }

    public void addEmail(String address) {
        //
        CastellanEmail castellanEmail = new CastellanEmail(address);
        castellanEmail.setRegisteredTime(ZonedDateTime.now());
        this.emails.add(castellanEmail);
    }

    public void removeEmail(String address) {
        //
        CastellanEmail email = findEmail(address);
        this.emails.remove(email);
    }

    public void verifyEmail(String address) {
        //
        CastellanEmail email = findEmail(address);
        email.verifyEmail();
    }

    public CastellanEmail findEmail(String address) {
        //
        for(CastellanEmail castellanEmail : this.emails) {
            if (address.equals(castellanEmail.getAddress())) {
                return castellanEmail;
            }
        }
        return null;
    }

    public Set<CastellanEmail> getEmails() {
        return emails;
    }

    public void setEmails(Set<CastellanEmail> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "EmailBook{" +
                "emails=" + emails +
                '}';
    }

    public static EmailBook getSample() {
        //
        EmailBook emailBook = EmailBook.newInstance("1");
        emailBook.addEmail("kchuh@nextree.co.kr");
        emailBook.addEmail("michael7557@gmail.com");
        emailBook.addEmail("michael7557@naver.com");

        emailBook.verifyEmail("kchuh@nextree.co.kr");
        return emailBook;
    }

    public static void main(String[] args) {
        //
        EmailBook sample = EmailBook.getSample();
        System.out.println(sample);
    }

}
