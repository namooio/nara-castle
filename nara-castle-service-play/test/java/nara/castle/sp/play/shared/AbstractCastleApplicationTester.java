package nara.castle.sp.play.shared;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.test.WithServer;

public abstract class AbstractCastleApplicationTester extends WithServer {
    //
    static final protected Logger logger = LoggerFactory.getLogger(AbstractCastleApplicationTester.class);

    static private MongoTestServer mongo;

    protected int serverPort;
    protected String baseUrl;
    protected ResourceTestClient client;

    static private Config config = ConfigFactory.load();


    @BeforeClass
    public static void beforeClass() throws Exception {
        //
//        final Config config = ConfigFactory.load();
        final int port = config.getInt("mongo.port");
        final String databaseName= config.getString("mongo.database");

        mongo = new MongoTestServer(port, databaseName);
//        mongo.start();

        String mongoUri = config.getString("mongo.uri");
        logger.info("Mongo test uri is {}", mongoUri);
    }
    @AfterClass
    public static void afterClass() throws Exception {
        //
//        mongo.stop();
    }

    @Before
    public void before() throws Exception {
        //
        this.serverPort = this.testServer.port();
        this.baseUrl = "http://localhost:" + serverPort + "/castle-api";

        client = new ResourceTestClient(this.serverPort);
        mongo.initializeData("CA_CASTLE", "/data/castle.json");
        mongo.initializeData("CA_CASTELLAN", "/data/castellan.json");
        mongo.initializeData("N_RESUME_OFFSET", "/data/resume-offset.json");
    }

    public String buildUrl(String urlPath) {
        return this.baseUrl + urlPath;
    }

}
