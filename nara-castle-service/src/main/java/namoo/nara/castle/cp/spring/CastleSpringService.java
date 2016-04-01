package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kchuh@nextree.co.kr on 2016. 3. 15..
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CastleSpringService extends CastleServiceLogic {

    @Autowired
    public CastleSpringService(CastleStoreLycler lifecycler) {
        super(lifecycler);
    }
}
