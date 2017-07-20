package nara.castle.cp;

import nara.castle.da.mongo.CastellanViewMongoStore;
import nara.castle.da.mongo.CastleViewMongoStore;
import nara.castle.domain.castlequery.store.CastellanViewStore;
import nara.castle.domain.castlequery.store.CastleViewStore;
import nara.castle.domain.castlequery.store.CastleViewStoreLycler;
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
