package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.entity.UnitPlate;
import namoo.nara.castle.domain.event.local.CastellanCreatedEvent;
import namoo.nara.castle.domain.event.local.CastleBuiltEvent;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.spec.CastleEventProcess;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.domain.store.EnrollmentStore;
import namoo.nara.castle.domain.store.UnitPlateStore;

import java.util.List;

public class CastleEventProcessLogic implements CastleEventProcess {
    //
    private CastellanStore castellanStore;
    private EnrollmentStore enrollmentStore;
    private UnitPlateStore unitPlateStore;

    public CastleEventProcessLogic(CastleStoreLycler storeLycler, CastleProxyLycler proxyLycler) {
        //
        castellanStore = storeLycler.requestCastellanStore();
        enrollmentStore = storeLycler.requestEnrollmentStore();
        unitPlateStore = storeLycler.requestUnitPlateStore();
    }

    @Override
    public void createCastellan(CastleBuiltEvent castleBuiltEvent) {
        //
        MetroEnrollment enrollment = castleBuiltEvent.getEnrollment();
        Castellan castellan = new Castellan(enrollment);
        castellanStore.create(castellan);

        CastleContext.getLocalEventService().produce(new CastellanCreatedEvent(castellan));
    }

    @Override
    public void createEnrollment(CastleBuiltEvent castleBuiltEvent) {
        //
        MetroEnrollment enrollment = castleBuiltEvent.getEnrollment();
        enrollmentStore.create(enrollment);
    }

    @Override
    public void createUnitPlate(CastellanCreatedEvent castellanCreatedEvent) {
        //
        List<UnitPlate> unitPlates = castellanCreatedEvent.getCastellan().requestUnitPlates().getUnitPlates();
        unitPlateStore.create(unitPlates);
    }
}
