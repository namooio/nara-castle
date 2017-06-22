package namoo.nara.castle.domain.context;

import namoo.nara.castle.domain.event.handler.local.CastleCreatedWorker;
import namoo.nara.castle.domain.event.handler.local.EnrollmentAddedWorker;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.share.event.worker.EventService;

import java.util.NoSuchElementException;

public class CastleContext {
    //
    private static CastleContext singletonContext;

    private CastleStoreLycler storeLycler;
    private CastleProxyLycler proxyLycler;

    private EventService eventService;

    private CastleContext(CastleStoreLycler storeLycler, CastleProxyLycler proxyLycler) {
        //
        this.storeLycler = storeLycler;
        this.proxyLycler = proxyLycler;

        this.eventService = proxyLycler.requestEventService();
        initEventWorkers();
    }

    private void initEventWorkers() {
        //
        // local
        eventService.addEventHandler(new CastleCreatedWorker(storeLycler, proxyLycler));
        eventService.addEventHandler(new EnrollmentAddedWorker(storeLycler, proxyLycler));

        // global
//        eventService.addEventHandler(new );
    }

    public static CastleContext getInstance() {
        //
        if(singletonContext == null) {
            throw new NoSuchElementException(CastleContext.class.getSimpleName());
        }

        return singletonContext;
    }

    public static CastleContext newInstance(
            CastleStoreLycler storeLycler,
            CastleProxyLycler proxyLycler
            ) {
        //
        if(singletonContext == null) {
            synchronized (CastleContext.class) {
                if (singletonContext == null) {
                    singletonContext = new CastleContext(storeLycler, proxyLycler);
                }
            }
        }

        return singletonContext;
    }

}