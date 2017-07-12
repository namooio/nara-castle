package namoo.nara.castle.cp.play;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import namoo.nara.castle.cp.akka.actor.CastleActor;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.domain.store.UnitPlateStore;


public class CastleStoreAkkaLycler implements CastleStoreLycler {
    //

    @Override
    public CastleStore requestCastleStore() {

        ActorSystem system = CastleActorSystem.INSTANCE.getActorSystem();

        final TestKit storeMock = new TestKit(system);
        ActorRef castleStoreMockActor = storeMock.getTestActor();

//        final ActorRef castleActor = system.actorOf(CastleActor.props(castleStoreMockActor));
        return null;
    }

    @Override
    public CastellanStore requestCastellanStore() {
        return null;
    }

    @Override
    public UnitPlateStore requestUnitPlateStore() {
        return null;
    }
}
