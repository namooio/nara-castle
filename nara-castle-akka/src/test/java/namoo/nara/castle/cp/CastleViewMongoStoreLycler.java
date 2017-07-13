package namoo.nara.castle.cp;

import namoo.nara.castle.da.mongo.CastellanViewMongoStore;
import namoo.nara.castle.da.mongo.CastleViewMongoStore;
import namoo.nara.castle.domain.view.store.CastellanViewStore;
import namoo.nara.castle.domain.view.store.CastleViewStore;
import namoo.nara.castle.domain.view.store.CastleViewStoreLycler;
import org.mongodb.morphia.Datastore;

public class CastleViewMongoStoreLycler implements CastleViewStoreLycler {
    //
    private CastleViewStore castleViewStore;
    private CastellanViewStore castellanViewStore;

    public CastleViewMongoStoreLycler(Datastore datastore) {
        //
        this.castleViewStore = new CastleViewMongoStore(datastore);
        this.castellanViewStore = new CastellanViewMongoStore(datastore);
    }

    @Override
    public CastleViewStore requestCastleViewStore() {
        //
        return castleViewStore;
    }

    @Override
    public CastellanViewStore requestCastellanViewStore() {
        //
        return castellanViewStore;
    }
}
