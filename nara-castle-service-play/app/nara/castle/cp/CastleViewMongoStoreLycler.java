package nara.castle.cp;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.typesafe.config.ConfigFactory;
import nara.castle.domain.castlequery.store.CastellanRMStore;
import nara.castle.domain.castlequery.store.CastleViewStoreLycler;
import nara.castle.domain.castlequery.store.EnrollmentRMStore;
import nara.castle.domain.castlequery.store.UnitPlateRMStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.inject.Singleton;

@Singleton
public class CastleViewMongoStoreLycler implements CastleViewStoreLycler {
    //
//    private CastleViewStore castleViewStore;
    private CastellanRMStore castellanViewStore;

    public CastleViewMongoStoreLycler() {
        //
        Morphia morphia = new Morphia();
        morphia.mapPackage("nara.castle.da.mongo.document");

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
        String dbName = ConfigFactory.load().getString("mongo.database");
        MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoUri));

        Datastore datastore = morphia.createDatastore(mongoClient, dbName);
        datastore.ensureIndexes();

//        this.castleViewStore = new CastleViewMongoStore(datastore);
//        this.castellanViewStore = new CastellanViewMongoStore(datastore);
    }

    @Override
    public CastellanRMStore requestCastellanRMStore() {
        return null;
    }

    @Override
    public EnrollmentRMStore requestEnrollmentRMStore() {
        return null;
    }

    @Override
    public UnitPlateRMStore requestUnitPlateRMStore() {
        return null;
    }

    //    @Override
//    public CastleViewStore requestCastleViewStore() {
//        //
//        return castleViewStore;
//    }
//
//    @Override
//    public CastellanRMStore requestCastellanViewStore() {
//        //
//        return castellanViewStore;
//    }
}
