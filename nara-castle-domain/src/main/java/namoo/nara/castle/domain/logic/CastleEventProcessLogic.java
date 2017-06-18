package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.entity.UnitPlate;
import namoo.nara.castle.domain.event.local.CastleCreated;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;

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
    public void createCastellan(CastleCreated castleBuiltEvent) {
        //
        MetroEnrollment enrollment = castleBuiltEvent.getEnrollment();
        Castellan castellan = new Castellan(enrollment);
        castellanStore.create(castellan);

        CastleContext.getLocalEventService().produce(new CastellanCreatedEvent(castellan));
    }

    @Override
    public void createEnrollment(CastleCreated castleBuiltEvent) {
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
