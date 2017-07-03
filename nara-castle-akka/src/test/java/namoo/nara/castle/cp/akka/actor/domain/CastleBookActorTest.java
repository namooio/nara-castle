package namoo.nara.castle.cp.akka.actor.domain;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.query.castlebook.FindCastleBookQuery;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CastleBookActorTest {
    //
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
    public void testCastleBookActor() {
        //
        final TestKit storeMock = new TestKit(system);
        ActorRef castleStoreMockActor = storeMock.getTestActor();

        final TestKit testProbe = new TestKit(system);

        final ActorRef castleBookActor = system.actorOf(CastleBookActor.props(castleStoreMockActor));

        castleBookActor.tell(new NextSequenceCommand(), testProbe.getRef());
        testProbe.expectMsgEquals(new Long(1));

        castleBookActor.tell(new NextSequenceCommand(), testProbe.getRef());
        testProbe.expectMsgEquals(new Long(2));

        castleBookActor.tell(new NextSequenceCommand(), testProbe.getRef());
        testProbe.expectMsgEquals(new Long(3));

        castleBookActor.tell(new FindCastleBookQuery(), testProbe.getRef());
        CastleBook castleBook = testProbe.expectMsgClass(CastleBook.class);
        Assert.assertEquals(3, castleBook.getSequence());
    }
}
