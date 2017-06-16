package namoo.nara.castle.domain.event.global.handler;

import namoo.nara.castle.domain.logic.CastleEventProcessLogic;
import namoo.nara.share.event.NaraEvent;
import namoo.nara.share.event.NaraEventHandler;

public class SomeGlobalEventHandler implements NaraEventHandler<NaraEvent> {
    //
    private CastleEventProcessLogic castleEventProcessLogic;

    public SomeGlobalEventHandler(CastleEventProcessLogic castleEventProcessLogic) {
        //
        this.castleEventProcessLogic = castleEventProcessLogic;
    }

    @Override
    public void handle(NaraEvent event) {
        //
    }
}
