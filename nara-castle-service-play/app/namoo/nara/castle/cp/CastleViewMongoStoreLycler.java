package namoo.nara.castle.cp;

import com.mongodb.MongoClient;
import com.typesafe.config.ConfigFactory;
import namoo.nara.castle.da.mongo.CastellanViewMongoStore;
import namoo.nara.castle.da.mongo.CastleViewMongoStore;
import namoo.nara.castle.domain.view.store.CastellanViewStore;
import namoo.nara.castle.domain.view.store.CastleViewStore;
import namoo.nara.castle.domain.view.store.CastleViewStoreLycler;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.inject.Singleton;

@Singleton
public class CastleViewMongoStoreLycler implements CastleViewStoreLycler {
    //
    private CastleViewStore castleViewStore;
    private CastellanViewStore castellanViewStore;

    public CastleViewMongoStoreLycler() {
        //
        Morphia morphia = new Morphia();

        morphia.mapPackage("namoo.nara.castle.da.mongo.document");

        MongoClient mongoClient = new MongoClient(
                ConfigFactory.load().getString("mongo.host"),
                ConfigFactory.load().getInt("mongo.port"));

        Datastore datastore = morphia.createDatastore(mongoClient, ConfigFactory.load().getString("mongo.database"));

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
