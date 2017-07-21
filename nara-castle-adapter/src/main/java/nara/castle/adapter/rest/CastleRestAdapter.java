package nara.castle.adapter.rest;

import nara.castle.domain.castle.command.AddEnrollmentCommand;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.ModifyCastellanCommand;
import nara.castle.domain.castle.command.WithdrawMetroCommand;
import nara.castle.domain.castlequery.query.EnrolledCheckQuery;
import nara.castle.domain.castlequery.query.FindUnitPlatesQuery;
import nara.castle.spec.CastleService;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.RequestBuilder;

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
    public CompletionStage buildCastle(BuildCastleCommand buildCastleCommand) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTLE_BUILD)
                        .setRequestBody(buildCastleCommand)
                        .setResponseType(String.class)
        ));
    }

    @Override
    public CompletionStage modifyCastellan(String castellanId, ModifyCastellanCommand modifyCastellanCommand) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_MODIFY)
                .addPathParam("castellanId", castellanId)
                .setRequestBody(modifyCastellanCommand)
        ));
    }

    @Override
    public CompletionStage addEnrollment(String castellanId, AddEnrollmentCommand addEnrollmentCommand) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_ADD_ENROLLMENT)
                        .addPathParam("castellanId", castellanId)
                        .setRequestBody(addEnrollmentCommand)
        ));
    }

    @Override
    public CompletionStage withdrawMetro(String castellanId, WithdrawMetroCommand withdrawMetroCommand) {
        return null;
    }

    @Override
    public CompletionStage findCastellan(String castleId) {
        //
//        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND)
//                        .addPathParam("castleId", castleId)
//                        .setResponseType(Castle.class)
//        ));
        return null;
    }

    @Override
    public CompletionStage findCastellans() {
        //
//        return CompletableFuture.supplyAsync(() -> Arrays.asList(naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLANS_FIND)
//                        .setResponseType(CastellanView[].class)
//        )));
        return null;
    }

    @Override
    public CompletionStage findEnrollments(String castellanId) {
        return null;
    }

    @Override
    public CompletionStage findUnitPlate(FindUnitPlatesQuery findUnitPlatesQuery) {
        return null;
    }

    @Override
    public CompletionStage checkEnrolled(EnrolledCheckQuery enrolledCheckQuery) {
        return null;
    }
}
