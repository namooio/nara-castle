package nara.castle.adapter.rest;

import nara.castle.domain.castle.command.*;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import nara.castle.domain.castlequery.query.*;
import nara.castle.spec.CastleService;
import nara.share.domain.OffsetList;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class CastleRestAdapter implements CastleService {
    //
    private NaraRestClient naraRestClient;

    public CastleRestAdapter(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public CompletionStage<String> buildCastle(BuildCastleCommand buildCastleCommand) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTLE_BUILD)
                        .setRequestBody(buildCastleCommand)
                        .setResponseType(String.class)
        ));
    }

    @Override
    public CompletionStage<Void> modifyCastellan(ModifyCastellanCommand modifyCastellanCommand) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_MODIFY)
                .addPathParam("castellanId", modifyCastellanCommand.getCastellanId())
                .setRequestBody(modifyCastellanCommand)
        ));
    }

    @Override
    public CompletionStage demolishCastle(DemolishCastleCommand demolishCastleCommand) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTLE_DEMOLISH)
                .addPathParam("castellanId", demolishCastleCommand.getCastellanId())
        ));
    }

    @Override
    public CompletionStage<Void> addEnrollment(AddEnrollmentCommand addEnrollmentCommand) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_ENROLLMENT_ADD)
                        .addPathParam("castellanId", addEnrollmentCommand.getCastellanId())
                        .setRequestBody(addEnrollmentCommand)
        ));
    }

    @Override
    public CompletionStage<Void> withdrawMetro(WithdrawMetroCommand withdrawMetroCommand) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_ENROLLMENT_WIDTHRAW)
                .addPathParam("castellanId", withdrawMetroCommand.getCastellanId())
                .setRequestBody(withdrawMetroCommand)
        ));
    }

    @Override
    public CompletionStage<CastellanRM> findCastellan(FindCastellanQuery findCastellanQuery) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_FIND)
                .addPathParam("castellanId", findCastellanQuery.getCastellanId())
                .setResponseType(CastellanRM.class)
        ));
    }

    @Override
    public CompletionStage<List<CastellanRM>> findCastellans(FindCastellansQuery findCastellansQuery) {
        //
        return CompletableFuture.supplyAsync(() -> Arrays.asList(naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLANS_FIND)
                .setResponseType(CastellanRM[].class)
        )));
    }

    @Override
    public CompletionStage<OffsetList<CastellanRM>> findCastellansPage(FindCastellansPageQuery findCastellansPageQuery) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLANS_PAGE_FIND)
                .setResponseType(OffsetList.class)
        ));
    }

    @Override
    public CompletionStage<Boolean> existsCastellan(ExistenceCheckQuery existenceCheckQuery) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_CHECK)
                .addPathParam("castellanId", existenceCheckQuery.getCastellanId())
                .setResponseType(Boolean.class)
        ));
    }

    @Override
    public CompletionStage<List<EnrollmentRM>> findEnrollments(FindEnrollmentsQuery findEnrollmentsQuery) {
        //
        return CompletableFuture.supplyAsync(() -> Arrays.asList(naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_ENROLLMENTS_FIND)
                .addPathParam("castellanId", findEnrollmentsQuery.getCastellanId())
                .setResponseType(EnrollmentRM[].class)
        )));
    }

    @Override
    public CompletionStage<List<UnitPlateRM>> findUnitPlates(FindUnitPlatesQuery findUnitPlatesQuery) {
        //
        return CompletableFuture.supplyAsync(() -> Arrays.asList(naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_UNIT_PLATES_FIND)
                .addQueryParam("keyAttr", findUnitPlatesQuery.getKeyAttr().name())
                .addQueryParam("keyValue", findUnitPlatesQuery.getKeyValue())
                .setResponseType(UnitPlateRM[].class)
        )));
    }

    @Override
    public CompletionStage<Boolean> checkEnrolled(EnrolledCheckQuery enrolledCheckQuery) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_ENROLLMENTS_CHECK)
                .addPathParam("castellanId", enrolledCheckQuery.getCastellanId())
                .addPathParam("metroId", enrolledCheckQuery.getMetroId())
                .setResponseType(Boolean.class)
        ));
    }
}
