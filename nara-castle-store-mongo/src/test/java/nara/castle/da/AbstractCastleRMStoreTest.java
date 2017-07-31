package nara.castle.da;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import nara.castle.da.mongo.CastellanRMMongoStore;
import nara.castle.da.mongo.EnrollmentRMMongoStore;
import nara.castle.da.mongo.UnitPlateRMMongoStore;
import nara.castle.domain.castlequery.store.CastellanRMStore;
import nara.castle.domain.castlequery.store.EnrollmentRMStore;
import nara.castle.domain.castlequery.store.UnitPlateRMStore;
import org.junit.After;
import org.junit.Before;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.MapperOptions;

import java.io.IOException;

public abstract class AbstractCastleRMStoreTest {
    //
    private CastellanRMStore castellanRMStore;
    private EnrollmentRMStore enrollmentRMStore;
    private UnitPlateRMStore unitPlateRMStore;

    private MongodExecutable mongodExecutable;

    @Before
    public void setUp() throws IOException {
        //
        MongodStarter starter = MongodStarter.getDefaultInstance();

        String bindIp = "127.0.0.1";
        int port = 55555;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .build();

        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();

        MongoClient mongoClient = new MongoClient("127.0.0.1", 55555);

        Morphia morphia = new Morphia();
        morphia.mapPackage("nara.castle.da.mongo.document");
        MapperOptions mapperOptions = new MapperOptions();
        mapperOptions.setStoreEmpties(true);
        morphia.getMapper().setOptions(mapperOptions);

        Datastore datastore = morphia.createDatastore(mongoClient, "castle-test");
        datastore.ensureIndexes();

        castellanRMStore = new CastellanRMMongoStore(datastore);
        enrollmentRMStore = new EnrollmentRMMongoStore(datastore);
        unitPlateRMStore = new UnitPlateRMMongoStore(datastore);
    }

    @After
    public void shutdown() {
        //
        if (mongodExecutable != null) mongodExecutable.stop();
    }

    public CastellanRMStore getCastellanRMStore() {
        return castellanRMStore;
    }

    public EnrollmentRMStore getEnrollmentRMStore() {
        return enrollmentRMStore;
    }

    public UnitPlateRMStore getUnitPlateRMStore() {
        return unitPlateRMStore;
    }
}
