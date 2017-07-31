package nara.castle.cp;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.typesafe.config.ConfigFactory;
import nara.castle.da.mongo.CastellanRMMongoStore;
import nara.castle.da.mongo.EnrollmentRMMongoStore;
import nara.castle.da.mongo.UnitPlateRMMongoStore;
import nara.castle.domain.castlequery.store.CastellanRMStore;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.castle.domain.castlequery.store.EnrollmentRMStore;
import nara.castle.domain.castlequery.store.UnitPlateRMStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.MapperOptions;

import javax.inject.Singleton;

@Singleton
public class CastleRMMongoStoreLycler implements CastleRMStoreLycler {
    //
    private CastellanRMStore castellanRMStore;
    private EnrollmentRMStore enrollmentRMStore;
    private UnitPlateRMStore unitPlateRMStore;

    public CastleRMMongoStoreLycler() {
        //
        Morphia morphia = new Morphia();
        morphia.mapPackage("nara.castle.da.mongo.document");
        MapperOptions mapperOptions = new MapperOptions();
        mapperOptions.setStoreEmpties(true);
        morphia.getMapper().setOptions(mapperOptions);

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

        this.castellanRMStore = new CastellanRMMongoStore(datastore);
        this.enrollmentRMStore = new EnrollmentRMMongoStore(datastore);
        this.unitPlateRMStore = new UnitPlateRMMongoStore(datastore);
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
