package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.CastleBook;

public interface CastleStore {
    //
    void create(Castle castle);
    Castle retrieve(String id);
    void update(Castle castle);
    void delete(Castle castle);
    void delete(String id);

    CastleBook retrieveCastleBook();
    CastleBook retrieveCastleBookWithNextCastleSequence();
    void updateCastleBook(CastleBook castleBook);

}