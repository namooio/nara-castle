package namoo.nara.castle.domain.event;

import namoo.nara.castle.domain.event.local.handler.CastellanCreatedEventHandler;
import namoo.nara.castle.domain.event.local.handler.CastleBuiltEventHandlerForCastellan;
import namoo.nara.castle.domain.event.local.handler.CastleBuiltEventHandlerForEnrollment;
import namoo.nara.castle.domain.logic.CastleEventProcessLogic;
import namoo.nara.share.event.NaraEventRouter;
import namoo.nara.share.event.local.LocalEventQueue;

public class CastleEventConfig {
    //
    private NaraEventRouter eventRouter;

    public CastleEventConfig(LocalEventQueue eventQueue, CastleEventProcessLogic castleEventProcessLogic) {
        //
        this.eventRouter = new NaraEventRouter(eventQueue);
        this.eventRouter.addHandler(new CastellanCreatedEventHandler(castleEventProcessLogic));
        this.eventRouter.addHandler(new CastleBuiltEventHandlerForCastellan(castleEventProcessLogic));
        this.eventRouter.addHandler(new CastleBuiltEventHandlerForEnrollment(castleEventProcessLogic));
    }

    public void startRouter() {
        //
        new Thread(this.eventRouter).start();
    }

}
