package nara.share.test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.test.WithServer;

public abstract class AbstractIntegrationTester extends WithServer {
    //
    static final protected Logger logger = LoggerFactory.getLogger(AbstractIntegrationTester.class);

    static final private Config config = ConfigFactory.load();
    static final private boolean useEmbedded = config.getBoolean("mongo.useEmbedded");
    static protected MongoTestServer mongo;

    private ResourceTestClient client;


    abstract protected String getResourceUrlPrefix();

    protected ResourceTestClient getClient() {
        //
        if (client == null) {
            client = new ResourceTestClient(this.testServer.port());
        }
        return client;
    }

    protected String buildUrl(String urlPath) {
        //
        return "http://localhost:" + this.testServer.port() + getResourceUrlPrefix() + urlPath;
    }


    @BeforeClass
    public static void beforeClass() throws Exception {
        //
        final int port = config.getInt("mongo.port");
        final String databaseName= config.getString("mongo.database");

        mongo = new MongoTestServer(port, databaseName);

        if (useEmbedded) {
            mongo.start();
        }

        String mongoUri = config.getString("mongo.uri");
        logger.info("Mongo test uri is {}", mongoUri);
    }
    @AfterClass
    public static void afterClass() throws Exception {
        //
        if (useEmbedded && mongo != null) {
            mongo.stop();
        }
    }

}
