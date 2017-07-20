package nara.castle.domain.castlequery.store;

import nara.castle.domain.castlequery.model.CastellanRM;

import java.util.List;

public interface CastellanRMStore {
    //
    void create(CastellanRM castellanView);
    CastellanRM retrieve(String id);
    List<CastellanRM> retrieveAll();
    void update(CastellanRM castellanView);
    void delete(String id);
    boolean exists(String id);
}