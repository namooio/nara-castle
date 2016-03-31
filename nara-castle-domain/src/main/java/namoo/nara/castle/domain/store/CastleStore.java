package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.Castle;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 1..
 */
public interface CastleStore {

    void create(Castle castle);

    Castle retrieve(String id);

    void update(Castle castle);

    void delete(String id);
}
