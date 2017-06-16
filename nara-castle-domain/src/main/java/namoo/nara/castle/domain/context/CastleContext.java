package namoo.nara.castle.domain.context;

import namoo.nara.castle.domain.event.local.handler.CastellanCreatedEventHandler;
import namoo.nara.castle.domain.event.local.handler.CastleBuiltEventHandlerForCastellan;
import namoo.nara.castle.domain.event.local.handler.CastleBuiltEventHandlerForEnrollment;
import namoo.nara.castle.domain.spec.CastleEventProcess;
import namoo.nara.castle.domain.spec.CastleServiceLycler;
import namoo.nara.share.event.NaraEventRouter;
import namoo.nara.share.event.local.LocalEventQueue;
import namoo.nara.share.event.local.LocalEventService;

public class CastleContext {
    //
    private static CastleIdBuilder castleIdBuilder = new CastleIdBuilder();
    private static LocalEventService localEventService;

    private static CastleServiceLycler serviceLycler;

    public static CastleIdBuilder getCastleIdBuilder() {
        //
        return castleIdBuilder;
    }

    public static LocalEventService getLocalEventService() {
        //
        return localEventService;
    }

    public static void setServiceLycler(CastleServiceLycler serviceLycler) {
        //
        CastleContext.serviceLycler = serviceLycler;
    }

    public static void initialize() {
        //
        // Create event queue
        LocalEventQueue eventQueue = new LocalEventQueue();

        // Create event router
        NaraEventRouter eventRouter = new NaraEventRouter(eventQueue);

        // Create local event service
        localEventService = new LocalEventService(eventQueue);

        CastleEventProcess castleEventProcess = serviceLycler.castleEventProcess();

        // Create local event handlers and add to router
        eventRouter.addHandler(new CastleBuiltEventHandlerForCastellan(castleEventProcess));
        eventRouter.addHandler(new CastleBuiltEventHandlerForEnrollment(castleEventProcess));
        eventRouter.addHandler(new CastellanCreatedEventHandler(castleEventProcess));

        // Start event router thread
        new Thread(eventRouter).start();
    }


}