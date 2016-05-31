package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.Castellan;

import java.util.List;

public interface CastellanStore {
    //
    void create(Castellan castellan);
    Castellan retrieve(String id);
    List<Castellan> retrieveAll();
    void update(Castellan castellan);
    void delete(String id);
}