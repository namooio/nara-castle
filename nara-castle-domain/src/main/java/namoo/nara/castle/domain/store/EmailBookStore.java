package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.contact.EmailBook;

public interface EmailBookStore {
    //
    void create(EmailBook emailBook);
    EmailBook retrieve(String id);
    void update(EmailBook emailBook);
    void delete(String id);
}
