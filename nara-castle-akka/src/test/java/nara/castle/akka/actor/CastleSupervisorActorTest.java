package nara.castle.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
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
import nara.share.akka.support.actor.result.ActorResult;
import nara.share.akka.support.projection.resume.inmem.InMemoryResumableProjection;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import nara.share.domain.protocol.NaraCommand;
import nara.castle.akka.projection.CastleProjectionActor;
import nara.castle.cp.CastleViewMongoStoreLycler;
import nara.castle.domain.castle.entity.Castle;
import nara.castle.domain.castle.entity.Enrollment;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castlequery.store.CastleViewStoreLycler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

public class CastleSupervisorActorTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private static CastleViewStoreLycler storeLycler;
    private static ActorSystem system;

    @BeforeClass
    public static void setup() throws IOException {
        //
        MongodStarter starter = MongodStarter.getDefaultInstance();

        String bindIp = "127.0.0.1";
        int port = 55555;

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

        MongoClient mongoClient = new MongoClient("127.0.0.1", 55555);

        Morphia morphia = new Morphia();
        morphia.mapPackage("nara.castle.da.mongo.document");
        Datastore datastore = morphia.createDatastore(mongoClient, "castle-test");
        datastore.ensureIndexes();

        storeLycler = new CastleViewMongoStoreLycler(datastore);

        system = ActorSystem.create("nara");
        system.actorOf(CastleProjectionActor.props(storeLycler, new InMemoryResumableProjection()));
    }

    @AfterClass
    public static void teardown() {
        //
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testCastleSupervisorActor() throws InterruptedException {
        //
        final TestKit testProbe = new TestKit(system);
        final ActorRef castleSupervisorActor = system.actorOf(CastleSupervisorActor.props(storeLycler), "castle-supervisor");

        NaraCommand command = new BuildCastleCommand(
                new Enrollment("P0P", "C1@POP", new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr", new NaraZone(Locale.KOREA, "Asia/Seoul"))
        );

        castleSupervisorActor.tell(command, testProbe.getRef());
        ActorResult<Castle> castle = testProbe.expectMsgClass(ActorResult.class);

        logger.debug("{}", castle.get());

//        Thread.sleep(10000);

//        PatternsCS.ask(castleSupervisorActor, command, 1000).thenApply(response -> {
//            String castleId = (String) response;
//            logger.debug("", castleId);
//            return castleId;
//        });

//        Enrollment sample = Enrollment.getSample();
//        EnrollmentCommand enrollMetroCommand = new EnrollmentCommand(sample);
//        castleSupervisorActor.tell(enrollMetroCommand, testProbe.getRef());
//
//        String castleId = testProbe.expectMsgClass(String.class);

//        String castleId = castle.getId();
//        castleSupervisorActor.tell(new FindCastleQuery(castleId), testProbe.getRef());
//        castle = testProbe.expectMsgClass(Castle.class);
//        logger.debug("{}", castle);
//
//        NameValueList nameValues = new NameValueList();
//        Name modifiedName = new Name(Locale.KOREAN, "기철", "허");
//        nameValues.add("name", modifiedName.toJson());
//        nameValues.add("primaryEmail", "kchuh@nextree.co.kr");
//        castleSupervisorActor.tell(new ModifyCastleCommand(castleId, nameValues), testProbe.getRef());
//
//        castleId = castle.getId();
//        castleSupervisorActor.tell(new FindCastleQuery(castleId), testProbe.getRef());
//        castle = testProbe.expectMsgClass(Castle.class);
//        logger.debug("{}", castle);



//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Castle castle = castleStore.retrieve(castleId);
//        logger.debug("{}", castle);
    }
}
