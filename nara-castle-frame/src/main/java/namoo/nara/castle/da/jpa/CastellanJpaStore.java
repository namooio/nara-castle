package namoo.nara.castle.da.jpa;

import namoo.nara.castle.da.jpa.jpo.CastellanJpo;
import namoo.nara.castle.da.jpa.springdata.CastellanRepository;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.store.CastellanStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
@Repository
public class CastellanJpaStore implements CastellanStore {

    @Autowired
    private CastellanRepository castellanRepository;

    @Override
    public void create(Castellan castellan) {
        castellanRepository.save(CastellanJpo.create(castellan));
    }

    @Override
    public Castellan retrieve(String oid) {
        return castellanRepository.findOne(oid).toDomain();
    }

    @Override
    public void update(Castellan castellan) {
        castellanRepository.save(CastellanJpo.create(castellan));
    }

    @Override
    public void delete(String oid) {
        castellanRepository.delete(oid);
    }
}
