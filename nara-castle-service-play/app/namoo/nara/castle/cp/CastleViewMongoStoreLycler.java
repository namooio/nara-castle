package namoo.nara.castle.cp;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
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

//        String host = ConfigFactory.load().getString("mongo.host");
//        int port = ConfigFactory.load().getInt("mongo.port");
//
//        String database = ConfigFactory.load().getString("mongo.database");
//        String username = ConfigFactory.load().getString("mongo.username");
//        String password = ConfigFactory.load().getString("mongo.password");
//
//        List<ServerAddress> addresses = new ArrayList<>();
//        addresses.add(new ServerAddress(host, port));
//        List<MongoCredential> credentials = new ArrayList<>();
//        credentials.add(MongoCredential.createCredential(username, database, password.toCharArray()));

        String mongoUri = ConfigFactory.load().getString("mongo.uri");
        MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoUri));

        Datastore datastore = morphia.createDatastore(mongoClient, "nara_castle");
        datastore.ensureIndexes();

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
