package nara.castle.domain.castlequery.store;

import nara.castle.domain.castlequery.model.CastellanJsonRM;

public interface CastellanJsonRMStore {
    //
    void create(CastellanJsonRM castellanJsonRM);
    CastellanJsonRM retrieve(String id);
    String retrieveJson(String id);
    void update(CastellanJsonRM castellanJsonRM);
    void delete(String id);
    boolean exists(String id);
}