package namoo.nara.castle.cp;

import akka.actor.ActorSystem;
import namoo.nara.castle.akka.projection.CastleProjectionActor;
import namoo.nara.castle.domain.view.store.CastleViewStoreLycler;
import play.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CastleViewProjectionLycler {
    //
    final Logger.ALogger logger = Logger.of(this.getClass());

    @Inject
    public CastleViewProjectionLycler(ActorSystem system, CastleViewStoreLycler storeLycler) {
        //
        system.actorOf(CastleProjectionActor.props(storeLycler));
        logger.debug("Castle view projection actor started...");
    }
}
