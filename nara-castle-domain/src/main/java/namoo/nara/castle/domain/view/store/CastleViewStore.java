package namoo.nara.castle.domain.view.store;

import namoo.nara.castle.domain.view.CastleView;

import java.util.List;

public interface CastleViewStore {
    //
    void create(CastleView castleView);
    CastleView retrieve(String id);
    CastleView retrieveByEnrolledMetro(String metroId, String civilianId);
    List<CastleView> retrieveAll();
    void update(CastleView castle);
    void delete(String id);

}