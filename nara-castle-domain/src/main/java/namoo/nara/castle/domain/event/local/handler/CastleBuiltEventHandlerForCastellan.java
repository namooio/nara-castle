package namoo.nara.castle.domain.event.local.handler;

import namoo.nara.castle.domain.event.local.CastleBuiltEvent;
import namoo.nara.castle.domain.spec.CastleEventProcess;
import namoo.nara.share.event.NaraEventHandler;

public class CastleBuiltEventHandlerForCastellan implements NaraEventHandler<CastleBuiltEvent> {
    //
    private CastleEventProcess castleEventProcess;

    public CastleBuiltEventHandlerForCastellan(CastleEventProcess castleEventProcess) {
        //
        this.castleEventProcess = castleEventProcess;
    }

    @Override
    public void handle(CastleBuiltEvent event) {
        //
        castleEventProcess.createCastellan(event);
    }
}
