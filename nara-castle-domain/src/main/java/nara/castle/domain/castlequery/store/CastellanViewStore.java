package nara.castle.domain.castlequery.store;

import nara.castle.domain.castlequery.model.CastellanView;

import java.util.List;

public interface CastellanViewStore {
    //
    void create(CastellanView castellanView);
    CastellanView retrieve(String id);
    List<CastellanView> retrieveAll();
    void update(CastellanView castellanView);
    void delete(String id);
    boolean exists(String id);
}