package namoo.nara.castle.cp.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import namoo.nara.castle.cp.akka.actor.domain.CastleSupervisorActor;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.domain.granule.NaraZone;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class CastleSupervisorActorTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        //
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        //
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testCastleServiceActor() {
        //
        final TestKit storeMock = new TestKit(system);
        ActorRef castleStoreMockActor = storeMock.getTestActor();

        final TestKit testProbe = new TestKit(system);
        final ActorRef castleServiceActor = system.actorOf(CastleSupervisorActor.props(castleStoreMockActor), "castle-supervisor");

        MetroEnrollment sample = MetroEnrollment.getSample();
        String metroId = sample.getMetroId();
        String civilianId = sample.getCivilianId();
        Name name = sample.getName();
        String email = sample.getEmail();
        NaraZone zone = sample.getZone();

        EnrollMetroCommand enrollMetroCommand = new EnrollMetroCommand(metroId, civilianId, name, email, zone);
        castleServiceActor.tell(enrollMetroCommand, testProbe.getRef());
        Castle castle = testProbe.expectMsgClass(Castle.class);
        logger.debug("{}", castle);

        enrollMetroCommand = new EnrollMetroCommand(metroId, civilianId, name, email, zone);
        castleServiceActor.tell(enrollMetroCommand, testProbe.getRef());
        castle = testProbe.expectMsgClass(Castle.class);
        logger.debug("{}", castle);

        enrollMetroCommand = new EnrollMetroCommand(metroId, civilianId, name, email, zone);
        castleServiceActor.tell(enrollMetroCommand, testProbe.getRef());
        castle = testProbe.expectMsgClass(Castle.class);
        logger.debug("{}", castle);

        String castleId = castle.getId();
        castleServiceActor.tell(new FindCastleQuery(castleId), testProbe.getRef());
        castle = testProbe.expectMsgClass(Castle.class);
        logger.debug("{}", castle);

        NameValueList nameValues = new NameValueList();
        Name modifiedName = new Name(Locale.KOREAN, "기철", "허");
        nameValues.add("name", modifiedName.toJson());
        nameValues.add("primaryEmail", "kchuh@nextree.co.kr");
        castleServiceActor.tell(new ModifyCastleCommand(castleId, nameValues), testProbe.getRef());

        castleId = castle.getId();
        castleServiceActor.tell(new FindCastleQuery(castleId), testProbe.getRef());
        castle = testProbe.expectMsgClass(Castle.class);
        logger.debug("{}", castle);
    }
}
