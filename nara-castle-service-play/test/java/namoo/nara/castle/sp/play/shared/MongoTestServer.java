package namoo.nara.castle.sp.play.shared;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.IOException;
import java.net.URL;

public class MongoTestServer {
    //
    static final private String MONGO_IP = "127.0.0.1";
    static final private int MONGO_PORT = 37016;
    static final private String MONGO_DB_NAME = "nara_castle";
    static final private String MONGO_COLLECTION = "CA_CASTLE";

    private MongodExecutable mongodExecutable;
    private MongodProcess mongodProcess;


    public void start() throws IOException {
        //
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(MONGO_IP, MONGO_PORT, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();

        this.mongodExecutable = starter.prepare(mongodConfig);
        this.mongodProcess = mongodExecutable.start();

        MongoClient mongo = new MongoClient(MONGO_IP, MONGO_PORT);

//        DB db = mongo.getDB(MONGO_DB_NAME);
//        DBCollection col = db.createCollection("testCol", new BasicDBObject());
//        col.save(new BasicDBObject("testDoc", new Date()));

        /*
        MongoClient mongoClient = new MongoClient(MONGO_IP, MONGO_PORT);

        Morphia morphia = new Morphia();
        morphia.mapPackage("namoo.nara.castle.da.mongo.document");
        Datastore datastore = morphia.createDatastore(mongoClient, MONGO_DB_NAME);
        datastore.ensureIndexes();

        return datastore;
        */
    }

    public MongoImportProcess initializeData(String jsonFilePath) throws IOException {
        //
        jsonFilePath = this.getClass().getResource(jsonFilePath).getFile();

        IMongoImportConfig mongoImportConfig = new MongoImportConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(MONGO_IP, MONGO_PORT, Network.localhostIsIPv6()))
                .db(MONGO_DB_NAME)
                .collection(MONGO_COLLECTION)
                .upsert(false)
                .dropCollection(true)
                .jsonArray(true)
                .importFile(jsonFilePath)
                .build();

        MongoImportExecutable mongoImportExecutable = MongoImportStarter.getDefaultInstance().prepare(mongoImportConfig);
        MongoImportProcess mongoImport = mongoImportExecutable.start();
        return mongoImport;
    }

    public void stop() {
        //
        if (mongodProcess != null) {
            mongodProcess.stop();
        }
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }
}
