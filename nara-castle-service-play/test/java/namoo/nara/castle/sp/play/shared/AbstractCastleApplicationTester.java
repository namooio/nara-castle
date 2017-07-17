package namoo.nara.castle.sp.play.shared;

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


    @BeforeClass
    public static void beforeClass() throws Exception {
        //
        mongo = new MongoTestServer();
        mongo.start();
        mongo.initializeData("/data/castle.json");
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
    }

    public String buildUrl(String urlPath) {
        return this.baseUrl + urlPath;
    }

}
