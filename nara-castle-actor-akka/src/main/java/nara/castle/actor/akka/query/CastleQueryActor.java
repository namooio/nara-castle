package nara.castle.actor.akka.query;

import akka.actor.Props;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import nara.castle.domain.castlequery.query.*;
import nara.castle.domain.castlequery.store.CastellanRMStore;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.castle.domain.castlequery.store.EnrollmentRMStore;
import nara.castle.domain.castlequery.store.UnitPlateRMStore;
import nara.share.actor.akka.NaraActor;
import nara.share.domain.OffsetList;
import nara.share.domain.protocol.NaraQuery;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            responseAsyncResult(() -> castellanRMStore.retrieve(castellanId));
        })
        .match(FindCastellansQuery.class, findCastellansQuery -> {
            //
            responseAsyncResult(() -> {
                KeyAttr keyAttr = findCastellansQuery.getKeyAttr();
                String keyValue = findCastellansQuery.getKeyValue();
                int limit = findCastellansQuery.getLimit();

                List<UnitPlateRM> unitPlateRMS = unitPlateRMStore.retrieve(keyAttr, keyValue, limit);
                Set<String> castellanIds = unitPlateRMS.stream().map(unitPlateRM -> unitPlateRM.getCastellanId()).collect(Collectors.toSet());
                return castellanRMStore.retrieveByCastellanIds(castellanIds);
            });
        })
        .match(FindCastellansPageQuery.class, findCastellansPageQuery ->{
            //
            int page = findCastellansPageQuery.getPage();
            int limit = findCastellansPageQuery.getLimit();

            responseAsyncResult(() -> {
                int offset = (page - 1) * limit;
                long totalCount = castellanRMStore.count();
                List<CastellanRM> castellanRMS = castellanRMStore.retrieve(offset, limit);
                return new OffsetList<>(castellanRMS, totalCount);
            });
        })
        .match(ExistenceCheckQuery.class, existenceCheckQuery -> {
            //
            String castellanId = existenceCheckQuery.getCastellanId();
            responseAsyncResult(() -> castellanRMStore.exists(castellanId));
        })
        .match(FindEnrollmentsQuery.class, findEnrollmentsQuery -> {
            //
            String castellanId = findEnrollmentsQuery.getCastellanId();
            responseAsyncResult(() -> enrollmentRMStore.retrieveByCastellanId(castellanId));
        })
        .match(EnrolledCheckQuery.class, enrolledCheckQuery -> {
            //
            String castellanId = enrolledCheckQuery.getCastellanId();
            String metroId = enrolledCheckQuery.getMetroId();
            responseAsyncResult(() -> {
                List<EnrollmentRM> enrollmentRMS = enrollmentRMStore.retrieveByCastellanId(castellanId);
                EnrollmentRM found = enrollmentRMS.stream().filter(enrollmentRM -> enrollmentRM.getMetroId().equals(metroId)).findFirst().orElse(null);
                return found != null;
            });
        })
        .match(FindUnitPlatesQuery.class, findUnitPlatesQuery -> {
            //
            KeyAttr keyAttr = findUnitPlatesQuery.getKeyAttr();
            String keyValue = findUnitPlatesQuery.getKeyValue();
            int limit = findUnitPlatesQuery.getLimit();
            responseAsyncResult(() -> unitPlateRMStore.retrieve(keyAttr, keyValue, limit));
        })
        .onMessage(query);
    }

}
