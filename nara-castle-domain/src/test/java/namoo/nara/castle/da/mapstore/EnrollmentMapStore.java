package namoo.nara.castle.da.mapstore;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.store.EnrollmentStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnrollmentMapStore implements EnrollmentStore {
    //
    private Map<String, MetroEnrollment> enrollmentMap = new HashMap<>();

    @Override
    public void create(MetroEnrollment enrollment) {
        //
        this.enrollmentMap.put(enrollment.getId(), enrollment);
    }

    @Override
    public MetroEnrollment retrieve(String id) {
        //
        return this.enrollmentMap.get(id);
    }

    @Override
    public MetroEnrollment retrieve(String metroId, String civilianId) {
        //
        return this.enrollmentMap.values()
                .stream()
                .filter(enrollment -> enrollment.getMetroId().equals(metroId) && enrollment.getCivilianId().equals(civilianId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<MetroEnrollment> retrieveByCastleId(String castleId) {
        //
        return this.enrollmentMap.values()
                .stream()
                .filter(enrollment -> enrollment.getCastleId().equals(castleId))
                .collect(Collectors.toList());
    }

    @Override
    public void update(MetroEnrollment enrollment) {
        //
        this.enrollmentMap.put(enrollment.getId(), enrollment);
    }

    @Override
    public void delete(MetroEnrollment enrollment) {
        //
        this.enrollmentMap.remove(enrollment.getId());
    }
}
