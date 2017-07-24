package nara.castle.actor.akka.query;

import akka.actor.Props;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.query.*;
import nara.castle.domain.castlequery.store.CastellanRMStore;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.castle.domain.castlequery.store.EnrollmentRMStore;
import nara.castle.domain.castlequery.store.UnitPlateRMStore;
import nara.share.actor.akka.NaraActor;
import nara.share.domain.protocol.NaraQuery;

import java.util.List;

public class CastleQueryActor extends NaraActor {
    //
    private CastellanRMStore castellanRMStore;
    private EnrollmentRMStore enrollmentRMStore;
    private UnitPlateRMStore unitPlateRMStore;

    static public Props props(CastleRMStoreLycler rmStoreLycler) {
        //
        return Props.create(CastleQueryActor.class, () -> new CastleQueryActor(rmStoreLycler));
    }

    public CastleQueryActor(CastleRMStoreLycler rmStoreLycler) {
        //
        castellanRMStore = rmStoreLycler.requestCastellanRMStore();
        enrollmentRMStore = rmStoreLycler.requestEnrollmentRMStore();
        unitPlateRMStore = rmStoreLycler.requestUnitPlateRMStore();
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        matcher()
        .match(FindCastellanQuery.class, findCastellanQuery -> {
            //
            String castellanId = findCastellanQuery.getCastellanId();
            responseResult(() -> castellanRMStore.retrieve(castellanId));
        })
        .match(FindCastellansQuery.class, findCastellansQuery -> {
            //
            responseResult(() -> castellanRMStore.retrieveAll());
        })
        .match(ExistenceCheckQuery.class, existenceCheckQuery -> {
            //
            String castellanId = existenceCheckQuery.getCastellanId();
            responseResult(() -> castellanRMStore.exists(castellanId));
        })
        .match(FindEnrollmentsQuery.class, findEnrollmentsQuery -> {
            //
            String castellanId = findEnrollmentsQuery.getCastellanId();
            responseResult(() -> enrollmentRMStore.retrieveByCastellanId(castellanId));
        })
        .match(EnrolledCheckQuery.class, enrolledCheckQuery -> {
            //
            String castellanId = enrolledCheckQuery.getCastellanId();
            String metroId = enrolledCheckQuery.getMetroId();
            responseResult(() -> {
                List<EnrollmentRM> enrollmentRMS = enrollmentRMStore.retrieveByCastellanId(castellanId);
                EnrollmentRM found = enrollmentRMS.stream().filter(enrollmentRM -> enrollmentRM.getMetroId().equals(metroId)).findFirst().orElse(null);
                return found != null;
            });
        })
        .match(FindUnitPlatesQuery.class, findUnitPlatesQuery -> {
            //
            KeyAttr keyAttr = findUnitPlatesQuery.getKeyAttr();
            String keyValue = findUnitPlatesQuery.getKeyValue();
            responseResult(() -> unitPlateRMStore.retrieve(keyAttr, keyValue));
        })
        .onMessage(query);
    }
}
