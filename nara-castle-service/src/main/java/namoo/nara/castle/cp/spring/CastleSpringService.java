package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CastleSpringService extends CastleServiceLogic {

    @Autowired
    public CastleSpringService(CastleStoreLycler storeLycler) {
        //
        super(storeLycler);
    }
}
