package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.EmailBook;
import namoo.nara.castle.domain.entity.contact.UserEmail;

import java.util.ArrayList;
import java.util.List;

public class EmailBookMdo {
    //
    private List<UserEmailMdo> emailMdoList;

    public EmailBookMdo() {
        //
    }

    public static EmailBookMdo newInstance(EmailBook emailBook) {
        //
        EmailBookMdo emailBookMdo = new EmailBookMdo();
        List<UserEmail> userEmails = emailBook.findAll();
        if (userEmails != null) {
            for(UserEmail userEmail : userEmails) {
                emailBookMdo.addUserEmailMdo(UserEmailMdo.newInstance(userEmail));
            }
        }
        return emailBookMdo;
    }

    public EmailBook getDomain() {
        //
        EmailBook emailBook = new EmailBook();
        if (emailMdoList != null) {
            for(UserEmailMdo userEmailMdo : emailMdoList) {
                emailBook.addEmail(userEmailMdo.getDomain());
            }
        }
        return emailBook;
    }

    public void addUserEmailMdo(UserEmailMdo userEmailMdo) {
        //
        if (emailMdoList == null) emailMdoList = new ArrayList<>();
        emailMdoList.add(userEmailMdo);
    }

    public List<UserEmailMdo> getEmailMdoList() {
        return emailMdoList;
    }

    public void setEmailMdoList(List<UserEmailMdo> emailMdoList) {
        this.emailMdoList = emailMdoList;
    }
}