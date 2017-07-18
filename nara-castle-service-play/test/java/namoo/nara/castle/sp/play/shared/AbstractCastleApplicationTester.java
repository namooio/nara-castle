package namoo.nara.castle.sp.play.shared;

import com.typesafe.config.ConfigFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import play.Logger;
import play.test.WithServer;

public abstract class AbstractCastleApplicationTester extends WithServer {
    //
    static final protected Logger.ALogger logger = Logger.of(AbstractCastleApplicationTester.class);

    static private MongoTestServer mongo;

    protected int serverPort;
    protected String baseUrl;

    protected ResourceTestClient client;


    @BeforeClass
    public static void beforeClass() throws Exception {
        //
        mongo = new MongoTestServer();
        mongo.start();

        String mongoUri = ConfigFactory.load().getString("mongo.uri");
        logger.info("Mongo test uri is {}", mongoUri);
    }
    @AfterClass
    public static void afterClass() throws Exception {
        //
        mongo.stop();
    }

    @Before
    public void before() throws Exception {
        //
        this.serverPort = this.testServer.port();
        this.baseUrl = "http://localhost:" + serverPort + "/castle-api";

        client = new ResourceTestClient(this.serverPort);
        mongo.initializeData("CA_CASTLE", "/data/castle.json");
        mongo.initializeData("CA_CASTELLAN", "/data/castellan.json");
    }

    public String buildUrl(String urlPath) {
        return this.baseUrl + urlPath;
    }

}
