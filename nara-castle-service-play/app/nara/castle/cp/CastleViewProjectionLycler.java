package nara.castle.cp;

import akka.actor.ActorSystem;
import namoo.nara.share.akka.support.projection.resume.inmem.InMemoryResumableProjection;
import nara.castle.akka.projection.CastleProjectionActor;
import nara.castle.domain.view.store.CastleViewStoreLycler;
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
        // FIXME
        system.actorOf(CastleProjectionActor.props(storeLycler, new InMemoryResumableProjection()));
        logger.debug("Castle view projection actor started...");
    }
}
