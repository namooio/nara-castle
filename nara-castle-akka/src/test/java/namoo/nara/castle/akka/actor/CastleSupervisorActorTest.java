package namoo.nara.castle.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import namoo.nara.castle.CastleAkkaTestApplication;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.domain.granule.NaraZone;
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

import java.util.Locale;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CastleAkkaTestApplication.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CastleSupervisorActorTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CastleStore castleStore;

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
        final ActorRef castleSupervisorActor = system.actorOf(CastleSupervisorActor.props(castleStore), "castle-supervisor");

        MetroEnrollment sample = MetroEnrollment.getSample();
        String metroId = sample.getMetroId();
        String civilianId = sample.getCivilianId();
        Name name = sample.getName();
        String email = sample.getEmail();
        NaraZone zone = sample.getZone();

        EnrollMetroCommand enrollMetroCommand = new EnrollMetroCommand(metroId, civilianId, name, email, zone);
        castleSupervisorActor.tell(enrollMetroCommand, testProbe.getRef());

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

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
