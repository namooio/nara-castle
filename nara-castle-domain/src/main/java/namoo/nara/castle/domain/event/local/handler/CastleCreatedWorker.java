package namoo.nara.castle.domain.event.local.handler;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.event.global.CastellanFailEvent;
import namoo.nara.castle.domain.event.local.CastleCreated;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.share.event.handle.LocalEventHandler;
import namoo.nara.share.event.worker.EventService;

public class CastleCreatedWorker extends LocalEventHandler<CastleCreated> {
    //
    private CastellanStore castellanStore;

    public CastleCreatedWorker(CastleStoreLycler storeLycler) {
        //
        super(CastleCreated.class.getName());
        this.castellanStore = storeLycler.requestCastellanStore();
    }

    @Override
    public void process(CastleCreated event) {
        //
        MetroEnrollment enrollment = event.getEnrollment();
        Castellan castellan = new Castellan(enrollment);

        try {
            castellanStore.create(castellan);
        } catch (Exception e) {
            EventService eventService = CastleContext.getInstance().getEventService();
            String workerName = CastleCreatedWorker.class.getName();
            eventService.produce(new CastellanFailEvent(enrollment.getId(), workerName));
        }
    }
}