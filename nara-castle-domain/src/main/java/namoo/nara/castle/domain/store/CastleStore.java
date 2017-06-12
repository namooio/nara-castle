package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.CastleBook;

import java.util.List;

public interface CastleStore {
    //
    void create(Castle castle);
    Castle retrieve(String id);
    Castle retrieveByEmail(String email);
    Castle retrieveByJoinedMetro(String nationId, String metroId, String civilianId);
    List<Castle> retrieveByNationId(String nationId);
    void update(Castle castle);
    void delete(Castle castle);
    void delete(String id);

    void createCastleBook(CastleBook castleBook);
    CastleBook retrieveCastleBook();
    void updateCastleBook(CastleBook castleBook);

    @Deprecated
    long retrieveNextSequence(String nationId);
}