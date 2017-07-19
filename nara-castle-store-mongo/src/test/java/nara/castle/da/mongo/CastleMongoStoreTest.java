package nara.castle.da.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import nara.castle.domain.castle.entity.Castle;
import nara.castle.domain.castlequery.model.CastleView;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

public class CastleMongoStoreTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleViewMongoStore castleViewMongoStore;

    @Before
    public void setUp() throws IOException {
        //
        MongodStarter starter = MongodStarter.getDefaultInstance();

        String bindIp = "127.0.0.1";
        int port = 27017;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .build();

        MongodExecutable mongodExecutable = null;
        try {
            mongodExecutable = starter.prepare(mongodConfig);
            MongodProcess mongod = mongodExecutable.start();

            MongoClient mongo = new MongoClient(bindIp, port);
            DB db = mongo.getDB("test");
            DBCollection col = db.createCollection("testCol", new BasicDBObject());
            col.save(new BasicDBObject("testDoc", new Date()));

        } finally {
//            if (mongodExecutable != null)
//                mongodExecutable.stop();
        }

        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

        Morphia morphia = new Morphia();
        morphia.mapPackage("nara.castle.da.mongo.document");
        Datastore datastore = morphia.createDatastore(mongoClient, "castle-test");
        datastore.ensureIndexes();

        castleViewMongoStore = new CastleViewMongoStore(datastore);
    }

    @Test
    public void test() {
        //
        CastleView castleView = new CastleView(Castle.getSample());
        castleViewMongoStore.create(castleView);

        castleView = castleViewMongoStore.retrieve(castleView.getId());
        logger.debug("{}", castleView);
    }
}
