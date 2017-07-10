package namoo.nara.castle.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.query.castlebook.FindCastleBookQuery;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CastleBookActorTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    static ActorSystem system;

    @BeforeClass
    public static void setup() throws IOException {
        //
//        MongodStarter starter = MongodStarter.getDefaultInstance();
//
//        String bindIp = "localhost";
//        int port = 55555;
//        IMongodConfig mongodConfig = new MongodConfigBuilder()
//                .version(Version.Main.PRODUCTION)
//                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
//                .build();
//
//        MongodExecutable mongodExecutable = null;
//        try {
//            mongodExecutable = starter.prepare(mongodConfig);
//            MongodProcess mongod = mongodExecutable.start();
//
//            MongoClient mongo = new MongoClient(bindIp, port);
//            DB db = mongo.getDB("test");
//            DBCollection col = db.createCollection("testCol", new BasicDBObject());
//            col.save(new BasicDBObject("testDoc", new Date()));
//
//        } finally {
//            if (mongodExecutable != null)
//                mongodExecutable.stop();
//        }

        system = ActorSystem.create("nara");
    }

    @AfterClass
    public static void teardown() {
        //
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testCastleBookActor() {
        //
        final TestKit testProbe = new TestKit(system);

        String castleBookId = CastleIdBuilder.requestCastleBookId();
        final ActorRef castleBookActor = system.actorOf(CastleBookActor.props(castleBookId));

        castleBookActor.tell(new NextSequenceCommand(), testProbe.getRef());
        testProbe.expectMsgClass(Long.class);
//        testProbe.expectMsgEquals(new Long(1));

        castleBookActor.tell(new NextSequenceCommand(), testProbe.getRef());
        testProbe.expectMsgClass(Long.class);
//        testProbe.expectMsgEquals(new Long(2));

        castleBookActor.tell(new NextSequenceCommand(), testProbe.getRef());
        testProbe.expectMsgClass(Long.class);
//        testProbe.expectMsgEquals(new Long(3));

        castleBookActor.tell(new FindCastleBookQuery(), testProbe.getRef());
        CastleBook castleBook = testProbe.expectMsgClass(CastleBook.class);
//        Assert.assertEquals(3, castleBook.getSequence());
        logger.debug("{}", castleBook);
    }
}
