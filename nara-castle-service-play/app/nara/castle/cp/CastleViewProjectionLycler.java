package nara.castle.cp;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import namoo.nara.share.akka.support.projection.resume.ResumableProjection;
import namoo.nara.share.akka.support.projection.resume.mongo.MongoResumableProjection;
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
        String mongoUri = ConfigFactory.load().getString("mongo.uri");
        String dbName = ConfigFactory.load().getString("mongo.database");

        ResumableProjection resumableProjection = new MongoResumableProjection(mongoUri, dbName);

        system.actorOf(CastleProjectionActor.props(storeLycler, resumableProjection));
    }
}
