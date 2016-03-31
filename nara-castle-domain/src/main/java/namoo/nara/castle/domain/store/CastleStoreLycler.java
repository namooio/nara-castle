package namoo.nara.castle.domain.store;

/**
 * Created by kchuh@nextree.co.kr on 2016. 3. 15..
 */
public interface CastleStoreLycler {

    CastellanEmailStore requestCastellanEmailStore();

    CastellanNameStore requestCastellanNameStore();

    CastellanStore requestCastellanStore();

    CastleStore requestCastleStore();

}
