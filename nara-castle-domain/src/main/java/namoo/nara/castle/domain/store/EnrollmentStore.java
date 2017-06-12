package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.MetroEnrollment;

import java.util.List;

public interface EnrollmentStore {
    //
    void create(MetroEnrollment enrollment);
    MetroEnrollment retrieve(String id);
    MetroEnrollment retrieve(String metroId, String civilianId);
    List<MetroEnrollment> retrieveByCastleId(String castleId);
    void update(MetroEnrollment enrollment);
    void delete(MetroEnrollment enrollment);
}