package nara.castle.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import nara.castle.akka.actor.persistence.CastleBookActor;
import nara.castle.domain.castlebook.entity.CastleIdBuilder;
import nara.castle.domain.castlebook.entity.CastleBook;
import nara.castle.domain.castlebook.command.NextSequenceCommand;
import nara.castle.domain.castlequery.query.FindCastleBookQuery;
import nara.share.akka.support.actor.result.ActorResult;
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
        ActorResult<CastleBook> castleBook = testProbe.expectMsgClass(ActorResult.class);
        logger.debug("{}", castleBook.get());

        castleBookActor.tell(new NextSequenceCommand(), testProbe.getRef());
        testProbe.expectMsgClass(ActorResult.class);
        logger.debug("{}", castleBook.get());

        castleBookActor.tell(new NextSequenceCommand(), testProbe.getRef());
        testProbe.expectMsgClass(ActorResult.class);
        logger.debug("{}", castleBook.get());

        castleBookActor.tell(new FindCastleBookQuery(), testProbe.getRef());
        castleBook = testProbe.expectMsgClass(ActorResult.class);
        logger.debug("{}", castleBook.get());
    }
}
