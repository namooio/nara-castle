package nara.castle.adapter.rest;

import nara.castle.domain.castle.command.AddEnrollmentCommand;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.ModifyCastellanCommand;
import nara.castle.domain.castle.command.WithdrawMetroCommand;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import nara.castle.spec.CastleService;
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
    public CompletionStage<Void> modifyCastellan(String castellanId, ModifyCastellanCommand modifyCastellanCommand) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_MODIFY)
                .addPathParam("castellanId", castellanId)
                .setRequestBody(modifyCastellanCommand)
        ));
    }

    @Override
    public CompletionStage demolishCastle(String castellanId) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTLE_DEMOLISH)
                .addPathParam("castellanId", castellanId)
        ));
    }

    @Override
    public CompletionStage<Void> addEnrollment(String castellanId, AddEnrollmentCommand addEnrollmentCommand) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_ENROLLMENT_ADD)
                        .addPathParam("castellanId", castellanId)
                        .setRequestBody(addEnrollmentCommand)
        ));
    }

    @Override
    public CompletionStage<Void> withdrawMetro(String castellanId, WithdrawMetroCommand withdrawMetroCommand) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_ENROLLMENT_WIDTHRAW)
                .addPathParam("castellanId", castellanId)
                .setRequestBody(withdrawMetroCommand)
        ));
    }

    @Override
    public CompletionStage<CastellanRM> findCastellan(String castellanId) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_FIND)
                .addPathParam("castellanId", castellanId)
                .setResponseType(CastellanRM.class)
        ));
    }

    @Override
    public CompletionStage<List<CastellanRM>> findCastellans() {
        //
        return CompletableFuture.supplyAsync(() -> Arrays.asList(naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLANS_FIND)
                .setResponseType(CastellanRM[].class)
        )));
    }

    @Override
    public CompletionStage<Boolean> existsCastellan(String castellanId) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_CHECK)
                .addPathParam("castellanId", castellanId)
                .setResponseType(Boolean.class)
        ));
    }

    @Override
    public CompletionStage<List<EnrollmentRM>> findEnrollments(String castellanId) {
        //
        return CompletableFuture.supplyAsync(() -> Arrays.asList(naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_ENROLLMENTS_FIND)
                .setResponseType(EnrollmentRM[].class)
        )));
    }

    @Override
    public CompletionStage<List<UnitPlateRM>> findUnitPlates(KeyAttr keyAttr, String keyValue) {
        //
        return CompletableFuture.supplyAsync(() -> Arrays.asList(naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_UNIT_PLATES_FIND)
                .addQueryParam("keyAttr", keyAttr.name())
                .addQueryParam("keyValue", keyValue)
                .setResponseType(UnitPlateRM[].class)
        )));
    }

    @Override
    public CompletionStage<Boolean> checkEnrolled(String castellanId, String metroId) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_ENROLLMENTS_CHECK)
                .addPathParam("castellanId", castellanId)
                .addPathParam("metroId", metroId)
                .setResponseType(Boolean.class)
        ));
    }
}
