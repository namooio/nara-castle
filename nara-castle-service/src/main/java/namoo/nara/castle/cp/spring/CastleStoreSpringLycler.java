package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.store.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CastleStoreSpringLycler implements CastleStoreLycler {
    //
    @Autowired
    private CastleStore castleStore;

    @Override
    public CastleStore requestCastleStore() {
        //
        return castleStore;
    }

    @Override
    public CastellanStore requestCastellanStore() {
        return null;
    }

    @Override
    public EnrollmentStore requestEnrollmentStore() {
        return null;
    }

    @Override
    public UnitPlateStore requestUnitPlateStore() {
        return null;
    }
}
