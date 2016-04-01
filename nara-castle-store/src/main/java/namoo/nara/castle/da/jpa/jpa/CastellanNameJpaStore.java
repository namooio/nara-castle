package namoo.nara.castle.da.jpa.jpa;

import namoo.nara.castle.da.jpa.jpa.springdata.CastellanNameRepository;
import namoo.nara.castle.domain.store.CastellanNameStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
@Repository
public class CastellanNameJpaStore implements CastellanNameStore {

    @Autowired
    private CastellanNameRepository castellanNameRepository;

    @Override
    public void deleteByCastellanId(String castellanOid) {
        castellanNameRepository.deleteByCastellanOid(castellanOid);
    }
}
