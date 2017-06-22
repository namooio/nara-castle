package namoo.nara.castle.domain.event.handler;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.event.CastellanFailEvent;
import namoo.nara.castle.domain.event.EnrollmentAdded;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.share.event.handler.LocalEventHandler;
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

        String castleId = enrollment.getCastleId();
        Castellan castellan = castellanStore.retrieve(enrollment.getCastleId());

        if (castellan == null) {
            produceGlobalEvent(castleId);
        }

        boolean updated = castellan.checkName(enrollment.getName());
        updated = castellan.checkEmail(enrollment.getEmail());

        if(!updated) {
            return;
        }

        try {
            castellan.initUnitPlates();
            castellanStore.update(castellan);
        } catch (Exception e) {
            produceGlobalEvent(castleId);
        }
    }

    private void produceGlobalEvent(String castleId) {
        //
        String workerName = EnrollmentAddedWorker.class.getName();
        eventService.produce(new CastellanFailEvent(castleId, workerName));
    }
}