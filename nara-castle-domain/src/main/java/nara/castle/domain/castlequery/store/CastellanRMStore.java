package nara.castle.domain.castlequery.store;

import nara.castle.domain.castlequery.model.CastellanRM;

import java.util.List;

public interface CastellanRMStore {
    //
    void create(CastellanRM castellanRM);
    CastellanRM retrieve(String id);
    List<CastellanRM> retrieveAll();
    void update(CastellanRM castellanRM);
    void delete(String id);
    boolean exists(String id);
}