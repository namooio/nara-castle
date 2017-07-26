package nara.castle.domain.castlequery.store;

import nara.castle.domain.castlequery.model.EnrollmentRM;

import java.util.List;

public interface EnrollmentRMStore {
    //
    void create(EnrollmentRM enrollmentRM);
    void create(List<EnrollmentRM> enrollmentRMS);

    EnrollmentRM retrieve(String id);
    List<EnrollmentRM> retrieveByCastellanId(String castellanId);
    EnrollmentRM retrieveByMetroIdAndCitizenId(String metroId, String citizenId);
    void update(EnrollmentRM enrollmentRM);
    void delete(String id);
    void deleteByCastellanId(String castellanId);
    boolean exists(String id);
}