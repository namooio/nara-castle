package nara.castle.adapter.rest;

import nara.castle.domain.entity.Castle;
import nara.castle.domain.spec.CastleService;
import nara.castle.domain.spec.command.castle.BuildCastleCommand;
import nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import nara.castle.domain.view.CastellanView;
import nara.castle.domain.view.CastleView;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;

public class CastleRestAdapter implements CastleService {
    //
    private NaraRestClient naraRestClient;

    public CastleRestAdapter(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public String buildCastle(BuildCastleCommand command) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_BUILD)
                .setRequestBody(command)
                .setResponseType(String.class)
        );
    }

    @Override
    public void enrollMetro(String castleId, EnrollMetroCommand command) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_ENROLL_METRO)
                        .addPathParam("castleId", castleId)
                        .setRequestBody(command)
        );
    }

    @Override
    public Castle findCastle(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND)
                        .addPathParam("castleId", castleId)
                        .setResponseType(Castle.class)
        );
    }

    @Override
    public List<CastleView> findCastles() {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLES_FIND)
                .setResponseType(CastleView[].class)
        ));
    }

    @Override
    public List<CastellanView> findCastellans() {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLANS_FIND)
                        .setResponseType(CastellanView[].class)
        ));
    }
}
