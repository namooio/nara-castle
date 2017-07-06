package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.Castle;

public interface CastleStore {
    //
    void create(Castle castle);
    Castle retrieve(String id);
    Castle retrieveByEnrolledMetro(String metroId, String civilianId);
    void update(Castle castle);
    void delete(Castle castle);
}