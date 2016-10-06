package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.logic.CastellanServiceLogic;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CastellanSpringService extends CastellanServiceLogic {
    //
    @Autowired
    public CastellanSpringService(CastleStoreLycler storeLycler) {
        super(storeLycler);
    }
}
