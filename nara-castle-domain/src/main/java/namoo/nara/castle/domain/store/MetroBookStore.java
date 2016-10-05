package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.metro.MetroBook;

public interface MetroBookStore {
    //
    void create(MetroBook metroBook);
    MetroBook retrieve(String id);
    void update(MetroBook metroBook);
    void delete(String id);
}
