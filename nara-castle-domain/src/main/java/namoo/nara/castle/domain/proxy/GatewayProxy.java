package namoo.nara.castle.domain.proxy;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 17..
 */
public interface GatewayProxy {
    //
    void createNaraAccount(String castleId, String email, String password);
}
