package nara.castle.actor.akka.query;

import akka.actor.Props;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.query.*;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.share.actor.akka.NaraActor;
import nara.share.domain.protocol.NaraQuery;

import java.util.List;

public class CastleQueryActor extends NaraActor {
    //
    private CastleRMStoreLycler rmStoreLycler;

    static public Props props(CastleRMStoreLycler rmStoreLycler) {
        //
        return Props.create(CastleQueryActor.class, () -> new CastleQueryActor(rmStoreLycler));
    }

    public CastleQueryActor(CastleRMStoreLycler rmStoreLycler) {
        //
        this.rmStoreLycler = rmStoreLycler;
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        matcher()
            .match(FindCastellanQuery.class, findCastellanQuery -> {
                //
                String castellanId = findCastellanQuery.getCastellanId();
                responseResult(() -> rmStoreLycler.requestCastellanRMStore().retrieve(castellanId));
            })
            .match(FindCastellansQuery.class, findCastellansQuery -> {
                //
                responseResult(() -> rmStoreLycler.requestCastellanRMStore().retrieveAll());
            })
            .match(ExistenceCheckQuery.class, existenceCheckQuery -> {
                //
                String castellanId = existenceCheckQuery.getCastellanId();
                responseResult(() -> rmStoreLycler.requestCastellanRMStore().exists(castellanId));
            })
            .match(FindEnrollmentsQuery.class, findEnrollmentsQuery -> {
                //
                String castellanId = findEnrollmentsQuery.getCastellanId();
                responseResult(() -> rmStoreLycler.requestEnrollmentRMStore().retrieveByCastellanId(castellanId));
            })
            .match(EnrolledCheckQuery.class, enrolledCheckQuery -> {
                //
                String castellanId = enrolledCheckQuery.getCastellanId();
                String metroId = enrolledCheckQuery.getMetroId();
                responseResult(() -> {
                    List<EnrollmentRM> enrollmentRMS = rmStoreLycler.requestEnrollmentRMStore().retrieveByCastellanId(castellanId);
                    EnrollmentRM found = enrollmentRMS.stream().filter(enrollmentRM -> enrollmentRM.getMetroId().equals(metroId)).findFirst().orElse(null);
                    return found != null;
                });
            })
            .match(FindUnitPlatesQuery.class, findUnitPlatesQuery -> {
                //
                KeyAttr keyAttr = findUnitPlatesQuery.getKeyAttr();
                String keyValue = findUnitPlatesQuery.getKeyValue();
                responseResult(() -> rmStoreLycler.requestUnitPlateRMStore().retrieve(keyAttr, keyValue));
            })
        .onMessage(query);
    }
}
