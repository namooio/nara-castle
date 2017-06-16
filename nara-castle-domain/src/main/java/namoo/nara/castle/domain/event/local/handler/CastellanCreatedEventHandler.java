package namoo.nara.castle.domain.event.local.handler;

import namoo.nara.castle.domain.event.local.CastellanCreatedEvent;
import namoo.nara.castle.domain.logic.CastleEventProcessLogic;
import namoo.nara.share.event.NaraEventHandler;

public class CastellanCreatedEventHandler implements NaraEventHandler<CastellanCreatedEvent> {
    //
    private CastleEventProcessLogic castleEventProcessLogic;

    public CastellanCreatedEventHandler(CastleEventProcessLogic castleEventProcessLogic) {
        //
        this.castleEventProcessLogic = castleEventProcessLogic;
    }

    @Override
    public void handle(CastellanCreatedEvent event) {
        //
        castleEventProcessLogic.createUnitPlate(event);
    }
}
