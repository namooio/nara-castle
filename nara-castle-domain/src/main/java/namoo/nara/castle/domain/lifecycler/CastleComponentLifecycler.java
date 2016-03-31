package namoo.nara.castle.domain.lifecycler;

import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.store.CastellanEmailStore;
import namoo.nara.castle.domain.store.CastellanNameStore;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStore;

/**
 * Created by kchuh@nextree.co.kr on 2016. 3. 15..
 */
public interface CastleComponentLifecycler {

    CastellanEmailStore requestCastellanEmailStore();

    CastellanNameStore requestCastellanNameStore();

    CastellanStore requestCastellanStore();

    CastleStore requestCastleStore();

    CastleService requestCastleService();

}
