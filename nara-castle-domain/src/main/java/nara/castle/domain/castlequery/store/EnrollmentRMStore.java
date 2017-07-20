package nara.castle.domain.castlequery.store;

import nara.castle.domain.castlequery.model.EnrollmentRM;

import java.util.List;

public interface EnrollmentRMStore {
    //
    void create(EnrollmentRM enrollment);
    EnrollmentRM retrieve(String id);
    List<EnrollmentRM> retrieveByCastellanId(String castellanId);
    void update(EnrollmentRM enrollment);
    void delete(EnrollmentRM enrollment);
    boolean exists(String id);
}