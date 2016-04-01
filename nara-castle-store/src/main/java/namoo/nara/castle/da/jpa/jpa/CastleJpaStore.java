package namoo.nara.castle.da.jpa.jpa;

import namoo.nara.castle.da.jpa.jpa.jpo.CastleJpo;
import namoo.nara.castle.da.jpa.jpa.springdata.CastleRepository;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.store.CastleStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
@Repository
public class CastleJpaStore implements CastleStore {

    @Autowired
    private CastleRepository castleRepository;

    @Override
    public void create(Castle castle) {
        CastleJpo castleJpo = CastleJpo.create(castle);
        castleRepository.save(castleJpo);
        castle.setOid(castleJpo.getId());
    }

    @Override
    public Castle retrieve(String oid) {
        CastleJpo castleJpo = castleRepository.findOne(oid);
        return castleJpo.toDomain();
    }

    @Override
    public void update(Castle castle) {
        CastleJpo castleJpo = CastleJpo.create(castle);
        castleRepository.save(castleJpo);
    }

    @Override
    public void delete(String oid) {
        castleRepository.delete(oid);
    }
}
