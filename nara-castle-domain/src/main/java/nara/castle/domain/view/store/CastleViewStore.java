package nara.castle.domain.view.store;

import nara.castle.domain.view.CastleView;

import java.util.List;

public interface CastleViewStore {
    //
    void create(CastleView castleView);
    CastleView retrieve(String id);
    CastleView retrieveByEnrolledMetro(String metroId, String civilianId);
    List<CastleView> retrieveAll();
    void update(CastleView castle);
    void delete(String id);

    boolean exists(String id);
    boolean existsByEnrolledMetro(String metroId, String civilianId);
}