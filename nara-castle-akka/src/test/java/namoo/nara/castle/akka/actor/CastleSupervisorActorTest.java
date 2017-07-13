package namoo.nara.castle.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.PatternsCS;
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
import namoo.nara.castle.cp.CastleViewMongoStoreLycler;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
import namoo.nara.castle.domain.view.store.CastleViewStoreLycler;
import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.domain.granule.NaraZone;
import namoo.nara.share.domain.protocol.NaraCommand;
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
        morphia.mapPackage("namoo.nara.castle.da.mongo.document");
        Datastore datastore = morphia.createDatastore(mongoClient, "castle-test");
        datastore.ensureIndexes();

        storeLycler = new CastleViewMongoStoreLycler(datastore);

        system = ActorSystem.create("nara");
    }

    @AfterClass
    public static void teardown() {
        //
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testCastleSupervisorActor() {
        //
        final TestKit testProbe = new TestKit(system);
        final ActorRef castleSupervisorActor = system.actorOf(CastleSupervisorActor.props(storeLycler), "castle-supervisor");

        NaraCommand command = new BuildCastleCommand(
                new MetroEnrollment("P0P", "C1@POP", new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr", new NaraZone(Locale.KOREA, "Asia/Seoul"))
        );

        castleSupervisorActor.tell(command, testProbe.getRef());
        String castleId = testProbe.expectMsgClass(String.class);

        logger.debug("{}", castleId);

//        PatternsCS.ask(castleSupervisorActor, command, 1000).thenApply(response -> {
//            String castleId = (String) response;
//            logger.debug("", castleId);
//            return castleId;
//        });

//        MetroEnrollment sample = MetroEnrollment.getSample();
//        EnrollMetroCommand enrollMetroCommand = new EnrollMetroCommand(sample);
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
