package nara.castle.actor.akka.query;

import akka.actor.ActorRef;
import akka.actor.Props;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import nara.castle.domain.castlequery.query.*;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.share.actor.akka.NaraActor;
import nara.share.actor.akka.result.ActorResult;
import nara.share.domain.protocol.NaraQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
                ActorRef sender = getSender();
                String castellanId = findCastellanQuery.getCastellanId();
                CompletableFuture.runAsync(() -> {
                    CastellanRM castellanRM = rmStoreLycler.requestCastellanRMStore().retrieve(castellanId);
                    sender.tell(ActorResult.value(castellanRM), getSelf());
                });
            })
            .match(FindCastellansQuery.class, findCastellansQuery -> {
                //
                ActorRef sender = getSender();
                CompletableFuture.runAsync(() -> {
                   List<CastellanRM> castellanRMS = rmStoreLycler.requestCastellanRMStore().retrieveAll();
                    sender.tell(ActorResult.value(castellanRMS), getSelf());
                });
            })
            .match(FindEnrollmentsQuery.class, findEnrollmentsQuery -> {
                //
                ActorRef sender = getSender();
                String castellanId = findEnrollmentsQuery.getCastellanId();
                CompletableFuture.runAsync(() -> {
                    List<EnrollmentRM> enrollmentRMS = rmStoreLycler.requestEnrollmentRMStore().retrieveByCastellanId(castellanId);
                    sender.tell(ActorResult.value(enrollmentRMS), getSelf());
                });
            })
            .match(EnrolledCheckQuery.class, enrolledCheckQuery -> {
                //
                ActorRef sender = getSender();
                String castellanId = enrolledCheckQuery.getCastellanId();
                String metroId = enrolledCheckQuery.getMetroId();
                CompletableFuture.runAsync(() -> {
                    List<EnrollmentRM> enrollmentRMS = rmStoreLycler.requestEnrollmentRMStore().retrieveByCastellanId(castellanId);
                    EnrollmentRM found = enrollmentRMS.stream().filter(enrollmentRM -> enrollmentRM.getMetroId().equals(metroId)).findFirst().orElse(null);
                    sender.tell(ActorResult.value(found != null), getSelf());
                });
            })
            .match(FindUnitPlatesQuery.class, findUnitPlatesQuery -> {
                //
                ActorRef sender = getSender();
                KeyAttr keyAttr = findUnitPlatesQuery.getKeyAttr();
                String keyValue = findUnitPlatesQuery.getKeyValue();

                CompletableFuture.runAsync(() -> {
                    List<UnitPlateRM> unitPlateRMS = rmStoreLycler.requestUnitPlateRMStore().retrieve(keyAttr, keyValue);
                    sender.tell(ActorResult.value(unitPlateRMS), getSelf());
                });
            })
        .onMessage(query);
    }
}
