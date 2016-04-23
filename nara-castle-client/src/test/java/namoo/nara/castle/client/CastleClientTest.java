package namoo.nara.castle.client;

import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.jaxrs.JaxRSClient;

import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 23..
 */
public class CastleClientTest {
    //
    public void buildCastle() {
        //
        NaraRestClient naraRestClient = new JaxRSClient("http://localhost:9930");
        CastleClient castleClient = new CastleClient(naraRestClient);

        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("허기철");
        castleBuildDto.setEmail("michael7557@gmail.com");
        castleBuildDto.setLocale(Locale.KOREAN);
        castleClient.buildCastle("0000", castleBuildDto);
    }

    public static void main(String[] args) {
        //
        CastleClientTest castleClientTest = new CastleClientTest();
        castleClientTest.buildCastle();
    }
}
