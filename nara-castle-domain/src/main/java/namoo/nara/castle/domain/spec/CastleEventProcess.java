package namoo.nara.castle.domain.spec;

import namoo.nara.castle.domain.event.local.CastellanCreatedEvent;
import namoo.nara.castle.domain.event.local.CastleBuiltEvent;

public interface CastleEventProcess {
    //
    void createCastellan(CastleBuiltEvent castleBuiltEvent);
    void createEnrollment(CastleBuiltEvent castleBuiltEvent);
    void createUnitPlate(CastellanCreatedEvent castellanCreatedEvent);
}
