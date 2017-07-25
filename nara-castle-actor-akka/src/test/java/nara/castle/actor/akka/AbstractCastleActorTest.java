package nara.castle.actor.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import nara.castle.actor.akka.query.CastleQueryActor;
import nara.castle.da.mongo.CastleRMMongoStoreLycler;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

public abstract class AbstractCastleActorTest {
    //
    private MongodExecutable mongodExecutable;

    private ActorSystem actorSystem;
    private ActorRef castleQuery;
    private ActorRef castleSupervisor;

    private TestKit testKit;
    private ActorRef testProbe;

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

        // mongodb://nara:rjsemfwlak!2@localhost:27016/nara_castle?authMechanism=SCRAM-SHA-1
        CastleRMStoreLycler rmStoreLycler = new CastleRMMongoStoreLycler("mongodb://localhost:55555/nara_castle", "nara_castle");

        actorSystem = ActorSystem.create("nara-test");
        castleQuery = actorSystem.actorOf(CastleQueryActor.props(rmStoreLycler), "castle-query");
        castleSupervisor = actorSystem.actorOf(CastleSupervisorActor.props(castleQuery), "castle-supervisor");

        testKit = new TestKit(getActorSystem());
        testProbe = testKit.getRef();
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

    public ActorRef getCastleQuery() {
        //
        return castleQuery;
    }

    public ActorRef getCastleSupervisor() {
        //
        return castleSupervisor;
    }

    public TestKit getTestKit() {
        //
        return testKit;
    }

    public ActorRef getTestProbe() {
        //
        return testProbe;
    }
}
