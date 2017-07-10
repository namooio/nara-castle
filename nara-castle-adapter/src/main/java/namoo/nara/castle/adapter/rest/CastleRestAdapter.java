package namoo.nara.castle.adapter.rest;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
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
    public String enrollMetro(EnrollMetroCommand command) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_ENROLL_METRO)
                .setRequestBody(command)
                .setResponseType(String.class)
        );
    }

    @Override
    public List<Castle> findCastles() {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND_ALL)
                .setResponseType(Castle[].class)
        ));
    }

}
