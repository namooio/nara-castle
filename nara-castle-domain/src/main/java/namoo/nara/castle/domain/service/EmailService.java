package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.contact.EmailBook;

public interface EmailService {
    //
    void createEmailBook(String id);
    EmailBook findEmailBook(String id);
    void removeEmailBook(String id);

    void addEmail(String id, String email);
    void verifyEmail(String id, String email);
    void removeEmail(String id, String email);

}
