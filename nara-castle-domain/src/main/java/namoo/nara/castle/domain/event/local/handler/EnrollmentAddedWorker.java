package namoo.nara.castle.domain.event.local.handler;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.event.global.CastellanFailEvent;
import namoo.nara.castle.domain.event.local.EnrollmentAdded;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.share.event.handler.LocalEventHandler;
import namoo.nara.share.event.worker.EventService;

public class EnrollmentAddedWorker extends LocalEventHandler<EnrollmentAdded> {
    //
    private CastellanStore castellanStore;

    public EnrollmentAddedWorker(CastleStoreLycler storeLycler) {
        //
        super(EnrollmentAdded.class.getName());
        this.castellanStore = storeLycler.requestCastellanStore();
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
        EventService eventService = CastleContext.getInstance().getEventService();
        String workerName = EnrollmentAddedWorker.class.getName();
        eventService.produce(new CastellanFailEvent(castleId, workerName));
    }
}