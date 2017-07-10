package namoo.nara.castle.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import namoo.nara.castle.CastleAkkaTestApplication;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CastleAkkaTestApplication.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CastleSupervisorActorTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CastleStoreLycler storeLycler;

    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        //
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
