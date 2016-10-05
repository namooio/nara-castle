package namoo.nara.castle.domain.store.mapstore;

import namoo.nara.castle.domain.entity.contact.EmailBook;
import namoo.nara.castle.domain.store.EmailBookStore;

import java.util.HashMap;
import java.util.Map;

public class EmailBookMapStore implements EmailBookStore {
    //
    private Map<String, EmailBook> emailBookMap;

    public EmailBookMapStore() {
        //
        this.emailBookMap = new HashMap<>();
    }

    @Override
    public void create(EmailBook emailBook) {
        //
        this.emailBookMap.put(emailBook.getId(), emailBook);
    }

    @Override
    public EmailBook retrieve(String id) {
        //
        return this.emailBookMap.get(id);
    }

    @Override
    public void update(EmailBook emailBook) {
        //
        this.emailBookMap.put(emailBook.getId(), emailBook);
    }

    @Override
    public void delete(String id) {
        //
        this.emailBookMap.remove(id);
    }
}
