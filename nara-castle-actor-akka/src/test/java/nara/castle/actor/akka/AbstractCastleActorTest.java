package nara.castle.actor.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import nara.castle.actor.akka.query.CastleQueryActor;
import nara.castle.cp.CastleTestRMMongoStoreLycler;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import org.junit.After;
import org.junit.Before;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.io.IOException;

public abstract class AbstractCastleActorTest {
    //
    private MongodExecutable mongodExecutable;

    private ActorSystem actorSystem;
    private ActorRef castleQueryActor;
    private ActorRef castleSupervisorActor;

    private TestKit testProbe;
    private ActorRef testProbeActor;

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
        Datastore datastore = morphia.createDatastore(mongoClient, "castle-test");
        datastore.ensureIndexes();

        CastleRMStoreLycler rmStoreLycler = new CastleTestRMMongoStoreLycler(datastore);

        actorSystem = ActorSystem.create("test");
        castleQueryActor = actorSystem.actorOf(CastleQueryActor.props(rmStoreLycler));
        castleSupervisorActor = actorSystem.actorOf(CastleSupervisorActor.props(castleQueryActor));

        testProbe = new TestKit(getActorSystem());
        testProbeActor = testProbe.getRef();
    }

    @After
    public void shutdown() {
        //
        mongodExecutable.stop();

        TestKit.shutdownActorSystem(actorSystem);
        actorSystem = null;
    }

    public ActorSystem getActorSystem() {
        //
        return actorSystem;
    }

    public ActorRef getCastleQueryActor() {
        //
        return castleQueryActor;
    }

    public ActorRef getCastleSupervisorActor() {
        //
        return castleSupervisorActor;
    }

    public TestKit getTestProbe() {
        //
        return testProbe;
    }

    public ActorRef getTestProbeActor() {
        //
        return testProbeActor;
    }
}
