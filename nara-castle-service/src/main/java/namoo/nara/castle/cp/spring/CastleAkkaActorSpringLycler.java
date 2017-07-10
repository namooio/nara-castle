package namoo.nara.castle.cp.spring;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import namoo.nara.castle.akka.actor.CastleSupervisorActor;
import namoo.nara.castle.domain.view.store.CastleViewStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CastleAkkaActorSpringLycler {
    //
    private final ActorSystem system = ActorSystem.create("nara");

    @Autowired
    private CastleViewStoreLycler storeLycler;

    public ActorRef requestCastleSupervisorActor() {
        //
        return system.actorOf(CastleSupervisorActor.props(storeLycler));
    }
}
