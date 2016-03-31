package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.lifecycler.CastleComponentLifecycler;
import namoo.nara.castle.domain.service.logic.CastleServiceLogic;
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
    public CastleSpringService(CastleComponentLifecycler lifecycler) {
        super(lifecycler);
    }
}
