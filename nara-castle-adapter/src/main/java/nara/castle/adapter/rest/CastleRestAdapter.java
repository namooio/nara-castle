package nara.castle.adapter.rest;

import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;
import nara.castle.domain.entity.Castle;
import nara.castle.domain.spec.CastleService;
import nara.castle.domain.spec.command.castle.BuildCastleCommand;
import nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import nara.castle.domain.view.CastellanView;
import nara.castle.domain.view.CastleView;

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
    public CompletionStage buildCastle(BuildCastleCommand command) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTLE_BUILD)
                        .setRequestBody(command)
                        .setResponseType(String.class)
        ));
    }

    @Override
    public CompletionStage enrollMetro(String castleId, EnrollMetroCommand command) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_ENROLL_METRO)
                        .addPathParam("castleId", castleId)
                        .setRequestBody(command)
        ));
    }

    @Override
    public CompletionStage findCastle(String castleId) {
        //
        return CompletableFuture.supplyAsync(() -> naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND)
                        .addPathParam("castleId", castleId)
                        .setResponseType(Castle.class)
        ));
    }

    @Override
    public CompletionStage<List<Castle>> findCastles() {
        //
        return CompletableFuture.supplyAsync(() -> Arrays.asList(naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTLES_FIND)
                        .setResponseType(CastleView[].class)
        )));
    }

    @Override
    public CompletionStage findCastellans() {
        //
        return CompletableFuture.supplyAsync(() -> Arrays.asList(naraRestClient.sendAndRecieve(RequestBuilder.create(CastleRestUrl.URL_CASTELLANS_FIND)
                        .setResponseType(CastellanView[].class)
        )));
    }
}
