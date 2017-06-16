package namoo.nara.castle.domain.event.local.handler;

import namoo.nara.castle.domain.event.local.CastleBuiltEvent;
import namoo.nara.castle.domain.logic.CastleEventProcessLogic;
import namoo.nara.share.event.NaraEventHandler;

public class CastleBuiltEventHandlerForCastellan implements NaraEventHandler<CastleBuiltEvent> {
    //
    private CastleEventProcessLogic castleEventProcessLogic;

    public CastleBuiltEventHandlerForCastellan(CastleEventProcessLogic castleEventProcessLogic) {
        //
        this.castleEventProcessLogic = castleEventProcessLogic;
    }

    @Override
    public void handle(CastleBuiltEvent event) {
        //
        castleEventProcessLogic.createCastellan(event);
    }
}
