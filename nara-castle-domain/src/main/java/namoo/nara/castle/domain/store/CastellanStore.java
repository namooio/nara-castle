package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.Castellan;

public interface CastellanStore {
    //
    void create(Castellan castellan);
    Castellan retrieve(String id);
    void update(Castellan castellan);
    void delete(String id);
}