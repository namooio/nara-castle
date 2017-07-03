package namoo.nara.castle.da.mapstore;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.store.CastleStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CastleMapStore implements CastleStore {
    //
    private Map<String, Castle> castleMap = new HashMap<>();
    private Map<String, MetroEnrollment> enrollmentMap = new HashMap<>();
    private CastleBook castleBook = new CastleBook();

    @Override
    public void create(Castle castle, MetroEnrollment enrollment) {
        //
        this.castleMap.put(castle.getId(), castle);
//        this.enrollmentMap.put(enrollment.getId(), enrollment);
    }

    @Override
    public void createEnrollment(MetroEnrollment enrollment) {
        //
//        this.enrollmentMap.put(enrollment.getId(), enrollment);
    }

    @Override
    public Castle retrieve(String castleId) {
        //
        return this.castleMap.get(castleId);
    }

    @Override
    public Castle retrieveEager(String castleId) {
        //
        Castle castle = this.retrieve(castleId);
        castle.setEnrollments(this.retrieveEnrollmentsOfCastle(castleId));
        return castle;
    }

    @Override
    public MetroEnrollment retrieveEnrollment(String metroId, String civilianId) {
        //
        return this.enrollmentMap.values()
                .stream()
                .filter(enrollment -> enrollment.getMetroId().equals(metroId) && enrollment.getCivilianId().equals(civilianId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<MetroEnrollment> retrieveEnrollmentsOfCastle(String castleId) {
        //
//        return this.enrollmentMap.values()
//                .stream()
//                .filter(enrollment -> enrollment.getCastleId().equals(castleId))
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public void update(Castle castle) {
        //
        this.castleMap.put(castle.getId(), castle);
    }

    @Override
    public void updateEnrollment(MetroEnrollment enrollment) {
        //
//        this.enrollmentMap.put(enrollment.getId(), enrollment);
    }

    @Override
    public void delete(Castle castle) {
        //
        this.castleMap.remove(castle.getId());
    }

    @Override
    public void deleteEnrollment(MetroEnrollment enrollment) {
        //
//        this.enrollmentMap.remove(enrollment.getId());
    }

    @Override
    public CastleBook retrieveCastleBook() {
        //
        return this.castleBook;
    }

    @Override
    public CastleBook retrieveCastleBookWithNextCastleSequence() {
        //
        this.castleBook.nextSequence();
        return this.castleBook;
    }

}
