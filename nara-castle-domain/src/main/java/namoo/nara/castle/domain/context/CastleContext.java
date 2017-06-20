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

    private CastleStoreLycler storeLycler;
    private CastleProxyLycler proxyLycler;

    private CastleIdBuilder castleIdBuilder;
    private ContextEventService eventService;

    private CastleContext(CastleStoreLycler storeLycler, CastleProxyLycler proxyLycler) {
        //
        this.castleIdBuilder = new CastleIdBuilder();
        this.storeLycler = storeLycler;
        this.proxyLycler = proxyLycler;
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