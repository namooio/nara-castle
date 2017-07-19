package nara.share.test;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.config.io.ProcessOutput;
import de.flapdoodle.embed.process.io.Processors;
import de.flapdoodle.embed.process.io.Slf4jLevel;
import de.flapdoodle.embed.process.runtime.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MongoTestServer {
    //
    static final private Logger logger = LoggerFactory.getLogger(MongoTestServer.class);

    static final private String MONGO_IP = "127.0.0.1";
    static final private MongodStarter starter;
    static private IMongodConfig config;

    private int port;
    private String dbName;

    private MongodExecutable mongodExecutable;
    private MongodProcess mongodProcess;


    static {
        ProcessOutput processOutput = new ProcessOutput(
                Processors.logTo(logger, Slf4jLevel.INFO),
                Processors.logTo(logger, Slf4jLevel.ERROR),
                Processors.logTo(logger, Slf4jLevel.DEBUG)
        );

        IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
                .defaultsWithLogger(Command.MongoD, logger)
                .processOutput(processOutput)
                .build();

        starter = MongodStarter.getInstance(runtimeConfig);
    }


    public MongoTestServer(int port, String databaseName) {
        this.port = port;
        this.dbName = databaseName;
    }

    public void start() throws IOException {
        //
        try {
            config = new MongodConfigBuilder()
                    .version(Version.Main.V3_4)
                    .net(new Net(MONGO_IP, port, Network.localhostIsIPv6()))
                    .build();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        this.mongodExecutable = starter.prepare(config);
        this.mongodProcess = mongodExecutable.start();

        MongoClient mongo = new MongoClient(MONGO_IP, port);

//        DB db = mongo.getDB(MONGO_DB_NAME);
//        DBCollection col = db.createCollection("testCol", new BasicDBObject());
//        col.save(new BasicDBObject("testDoc", new Date()));

        /*
        MongoClient mongoClient = new MongoClient(MONGO_IP, MONGO_PORT);

        Morphia morphia = new Morphia();
        morphia.mapPackage("nara.castle.da.mongo.document");
        Datastore datastore = morphia.createDatastore(mongoClient, MONGO_DB_NAME);
        datastore.ensureIndexes();

        return datastore;
        */
    }

    public MongoImportProcess initializeData(String collectionName, String jsonFilePath) throws IOException {
        //
        jsonFilePath = this.getClass().getResource(jsonFilePath).getFile();

        IMongoImportConfig mongoImportConfig = new MongoImportConfigBuilder()
                .version(Version.Main.DEVELOPMENT)
                .net(new Net(MONGO_IP, port, Network.localhostIsIPv6()))
                .db(dbName)
                .collection(collectionName)
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
