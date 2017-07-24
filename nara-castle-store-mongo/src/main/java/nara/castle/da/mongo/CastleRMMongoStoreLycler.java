package nara.castle.da.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import nara.castle.domain.castlequery.store.CastellanRMStore;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.castle.domain.castlequery.store.EnrollmentRMStore;
import nara.castle.domain.castlequery.store.UnitPlateRMStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class CastleRMMongoStoreLycler implements CastleRMStoreLycler {
    //
    private CastellanRMStore castellanRMStore;
    private EnrollmentRMStore enrollmentRMStore;
    private UnitPlateRMStore unitPlateRMStore;

    public CastleRMMongoStoreLycler(String mongoUri, String dbName) {
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

        MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoUri));

        Datastore datastore = morphia.createDatastore(mongoClient, dbName);
        datastore.ensureIndexes();

        castellanRMStore = new CastellanRMMongoStore(datastore);
        enrollmentRMStore = new EnrollmentRMMongoStore(datastore);
        unitPlateRMStore = new UnitPlateRMMongoStore(datastore);
    }

    @Override
    public CastellanRMStore requestCastellanRMStore() {
        //
        return castellanRMStore;
    }

    @Override
    public EnrollmentRMStore requestEnrollmentRMStore() {
        //
        return enrollmentRMStore;
    }

    @Override
    public UnitPlateRMStore requestUnitPlateRMStore() {
        //
        return unitPlateRMStore;
    }
}
