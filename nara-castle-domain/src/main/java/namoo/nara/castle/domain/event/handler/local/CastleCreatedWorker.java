package namoo.nara.castle.domain.event.handler.local;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.event.CastellanFail;
import namoo.nara.castle.domain.event.CastleCreated;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.share.event.handler.LocalEventHandler;
import namoo.nara.share.event.worker.EventService;

public class CastleCreatedWorker extends LocalEventHandler<CastleCreated> {
    //
    private CastellanStore castellanStore;
    private EventService eventService;

    public CastleCreatedWorker(CastleStoreLycler storeLycler, CastleProxyLycler proxyLycler) {
        //
        super(CastleCreated.class.getName());
        this.castellanStore = storeLycler.requestCastellanStore();
        this.eventService = proxyLycler.requestEventService();
    }

    @Override
    public void process(CastleCreated event) {
        //
        MetroEnrollment enrollment = event.getEnrollment();
        Castellan castellan = new Castellan(enrollment);

        try {
            castellanStore.create(castellan);
        } catch (Exception e) {
            String workerName = CastleCreatedWorker.class.getName();
            eventService.produce(new CastellanFail(enrollment.getId(), workerName));
        }
    }
}