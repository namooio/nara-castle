package namoo.nara.castle.domain.event.local.handler;

import namoo.nara.castle.domain.event.local.CastellanCreatedEvent;
import namoo.nara.castle.domain.spec.CastleEventProcess;
import namoo.nara.share.event.NaraEventHandler;

public class CastellanCreatedEventHandler implements NaraEventHandler<CastellanCreatedEvent> {
    //
    private CastleEventProcess castleEventProcess;

    public CastellanCreatedEventHandler(CastleEventProcess castleEventProcess) {
        //
        this.castleEventProcess = castleEventProcess;
    }

    @Override
    public void handle(CastellanCreatedEvent event) {
        //
        castleEventProcess.createUnitPlate(event);
    }
}
