package namoo.nara.castle.domain.context;

import namoo.nara.castle.domain.event.local.handler.CastleCreatedWorker;
import namoo.nara.castle.domain.event.local.handler.EnrollmentAddedWorker;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.share.event.worker.ContextEventService;
import namoo.nara.share.event.worker.EventService;

import java.util.NoSuchElementException;

public class CastleContext {
    //
    private static CastleContext singletonContext;

    private CastleProxyLycler proxyLycler;
    private CastleStoreLycler storeLycler;

    private CastleIdBuilder castleIdBuilder;
    private ContextEventService eventService;

    private CastleContext(CastleProxyLycler proxyLycler, CastleStoreLycler storeLycler) {
        //
        this.castleIdBuilder = new CastleIdBuilder();
        this.proxyLycler = proxyLycler;
        this.storeLycler = storeLycler;
        this.eventService = new ContextEventService(proxyLycler.getGlobalEventProxy());
        this.initEventWorkers();
    }

    private void initEventWorkers() {
        //
        eventService.addEventHandler(new CastleCreatedWorker(storeLycler));
        eventService.addEventHandler(new EnrollmentAddedWorker(storeLycler));
    }

    public static CastleContext getInstance() {
        //
        if(singletonContext == null) {
            throw new NoSuchElementException(CastleContext.class.getSimpleName());
        }

        return singletonContext;
    }

    public static CastleContext newInstance(
            CastleProxyLycler proxyLycler,
            CastleStoreLycler storeLycler) {
        //
        if(singletonContext == null) {
            synchronized (CastleContext.class) {
                if (singletonContext == null) {
                    singletonContext = new CastleContext(proxyLycler, storeLycler);
                }
            }
        }

        return singletonContext;
    }

    public CastleIdBuilder getCastleIdBuilder() {
        //
        return castleIdBuilder;
    }

    public EventService getEventService() {
        //
        return eventService;
    }

    public CastleProxyLycler getProxyLycler() {
        return proxyLycler;
    }

    public CastleStoreLycler getStoreLycler() {
        return storeLycler;
    }
}