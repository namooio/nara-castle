package nara.castle.domain.castlequery.store;

import nara.castle.domain.castlequery.model.CastellanRM;

import java.util.List;
import java.util.Set;

public interface CastellanRMStore {
    //
    void create(CastellanRM castellanRM);
    CastellanRM retrieve(String id);
    List<CastellanRM> retrieveAll();
    List<CastellanRM> retrieve(int offset, int limit);
    long count();

    List<CastellanRM> retrieve(String lastCastellanId, int limit);
    List<CastellanRM> retrieveByCastellanIds(Set<String> castellanIds);
    void update(CastellanRM castellanRM);
    void delete(String id);
    boolean exists(String id);


}