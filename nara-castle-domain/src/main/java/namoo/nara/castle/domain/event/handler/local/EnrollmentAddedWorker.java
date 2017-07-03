package namoo.nara.castle.domain.event.handler.local;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.event.CastellanFail;
import namoo.nara.castle.domain.event.EnrollmentAdded;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.share.event.handler.LocalEventHandler;
import namoo.nara.share.event.type.Event;
import namoo.nara.share.event.worker.EventService;

public class EnrollmentAddedWorker extends LocalEventHandler<EnrollmentAdded> {
    //
    private CastellanStore castellanStore;
    private EventService eventService;

    public EnrollmentAddedWorker(CastleStoreLycler storeLycler, CastleProxyLycler proxyLycler) {
        //
        super(EnrollmentAdded.class.getName());
        this.castellanStore = storeLycler.requestCastellanStore();
        this.eventService = proxyLycler.requestEventService();
    }

    @Override
    public void process(EnrollmentAdded event) {
        //
        MetroEnrollment enrollment = event.getEnrollment();

//        String castleId = enrollment.getCastleId();
//        Castellan castellan = castellanStore.retrieve(enrollment.getCastleId());

//        if (castellan == null) {
//            produceGlobalEvent(castleId, event);
//        }
//
//        boolean nameUpdated = castellan.checkName(enrollment.getName());
//        boolean emailUpdated = castellan.checkEmail(enrollment.getEmail());
//
//        if(!nameUpdated && !emailUpdated) {
//            return;
//        }
//
//        try {
//            castellan.initUnitPlates();
//            castellanStore.update(castellan);
//        } catch (Exception e) {
//            produceGlobalEvent(castleId, event);
//        }
    }

    private void produceGlobalEvent(String castleId, Event sourceEvent) {
        //
        String workerName = EnrollmentAddedWorker.class.getName();
        CastellanFail event = new CastellanFail(castleId, workerName);
        event.setSourceEvent(sourceEvent);
        eventService.produce(event);
    }
}