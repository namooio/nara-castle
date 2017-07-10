package namoo.nara.castle.cp.spring;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import namoo.nara.castle.akka.actor.CastleSupervisorActor;
import namoo.nara.castle.domain.store.CastleStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CastleActorLycler {
    //
    private final ActorSystem system = ActorSystem.create("nara");

    @Autowired
    private CastleStore castleStore;

    public ActorRef requestCastleSupervisorActor() {
        //
        return system.actorOf(CastleSupervisorActor.props(castleStore));
    }
}
