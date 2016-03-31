package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.Castellan;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 1..
 */
public interface CastellanStore {

    void create(Castellan castellan);

    Castellan retrieve(String id);

    void update(Castellan castellan);

    void delete(String id);
}
