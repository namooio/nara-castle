package namoo.nara.castle.da.mongo.document.contact;

import namoo.nara.castle.domain.entity.contact.EmailBook;
import namoo.nara.castle.domain.entity.contact.UserEmail;

import java.util.ArrayList;
import java.util.List;

public class EmailBookDoc {
    //
    private List<UserEmailDoc> emailList;

    public EmailBookDoc() {
        //
    }

    public static EmailBookDoc newInstance(EmailBook emailBook) {
        //
        EmailBookDoc emailBookDoc = new EmailBookDoc();
        List<UserEmail> userEmails = emailBook.findAll();
        if (userEmails != null) {
            for(UserEmail userEmail : userEmails) {
                emailBookDoc.addUserEmail(UserEmailDoc.newInstance(userEmail));
            }
        }
        return emailBookDoc;
    }

    public EmailBook toDomain() {
        //
        EmailBook emailBook = new EmailBook();
        if (emailList != null) {
            for(UserEmailDoc userEmailDoc : emailList) {
                emailBook.addEmail(userEmailDoc.toDomain());
            }
        }
        return emailBook;
    }

    public void addUserEmail(UserEmailDoc userEmail) {
        //
        if (emailList == null) emailList = new ArrayList<>();
        emailList.add(userEmail);
    }

    public List<UserEmailDoc> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<UserEmailDoc> emailList) {
        this.emailList = emailList;
    }
}