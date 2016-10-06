package namoo.nara.castle.client;

import namoo.nara.castle.adapter.CastleAdapter;
import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.share.restclient.NaraRestClient;

import java.util.List;
import java.util.Locale;

public class CastleClient implements CastleAdapter {
    //
    private NaraRestClient naraRestClient;

    public CastleClient(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public String buildCastle(CastleBuildDto castleBuildDto) {
        return null;
    }

    @Override
    public void modifyLocale(String castleId, Locale locale) {

    }

    @Override
    public CastleFindDto findCastle(String castleId) {
        return null;
    }

    @Override
    public List<CastleFindDto> findCastles() {
        return null;
    }
}
