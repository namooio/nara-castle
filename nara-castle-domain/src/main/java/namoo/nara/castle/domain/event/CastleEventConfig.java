package namoo.nara.castle.domain.event;

import namoo.nara.castle.domain.event.global.handler.SomeGlobalEventHandler;
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

        // Add local event handlers
        this.eventRouter.addHandler(new CastellanCreatedEventHandler(castleEventProcessLogic));
        this.eventRouter.addHandler(new CastleBuiltEventHandlerForCastellan(castleEventProcessLogic));
        this.eventRouter.addHandler(new CastleBuiltEventHandlerForEnrollment(castleEventProcessLogic));

        // Add global event handlers
        this.eventRouter.addHandler(new SomeGlobalEventHandler(castleEventProcessLogic));
    }

    public void startEventRouter() {
        //
        new Thread(this.eventRouter).start();
    }

}
