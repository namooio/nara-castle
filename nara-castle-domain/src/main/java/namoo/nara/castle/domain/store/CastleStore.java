package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.entity.MetroEnrollment;

import java.util.List;

public interface CastleStore {
    //
    void create(Castle castle, MetroEnrollment enrollment);
    void createEnrollment(MetroEnrollment enrollment);

    Castle retrieve(String castleId);
    Castle retrieveEager(String castleId);
    MetroEnrollment retrieveEnrollment(String metroId, String civilianId);
    List<MetroEnrollment> retrieveEnrollmentsOfCastle(String castleId);

    void update(Castle castle);
    void updateEnrollment(MetroEnrollment enrollment);

    void delete(Castle castle);
    void deleteEnrollment(MetroEnrollment enrollment);

    CastleBook retrieveCastleBook();
    CastleBook retrieveCastleBookWithNextCastleSequence();
}