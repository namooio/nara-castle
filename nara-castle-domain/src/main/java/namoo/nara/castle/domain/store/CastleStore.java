package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.Castle;

import java.util.List;

public interface CastleStore {
    //
    void create(Castle castle);
    Castle retrieve(String id);
    Castle retrieveByEmail(String email);
    List<Castle> retrieveAll();
    void update(Castle castle);
    void delete(String id);

    long retrieveNextSequence();
}