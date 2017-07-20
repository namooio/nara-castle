package nara.castle.domain.castlequery.store;

import nara.castle.domain.castlequery.model.EnrollmentRM;

import java.util.List;

public interface EnrollmentRMStore {
    //
    void create(EnrollmentRM enrollmentRM);
    EnrollmentRM retrieve(String id);
    List<EnrollmentRM> retrieveByCastellanId(String castellanId);
    void update(EnrollmentRM enrollmentRM);
    void delete(String id);
    boolean exists(String id);
}