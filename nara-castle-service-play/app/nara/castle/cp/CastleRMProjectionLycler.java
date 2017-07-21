package nara.castle.cp;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import nara.castle.actor.akka.projection.CastleProjectionActor;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.share.actor.akka.projection.journal.cassandra.CassandraReadJournalSource;
import nara.share.actor.akka.projection.resume.ResumableProjection;
import nara.share.actor.akka.projection.resume.mongo.MongoResumableProjection;
import play.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CastleRMProjectionLycler {
    //
    final Logger.ALogger logger = Logger.of(this.getClass());

    @Inject
    public CastleRMProjectionLycler(ActorSystem system, CastleRMStoreLycler storeLycler) {
        //
        String mongoUri = ConfigFactory.load().getString("mongo.uri");
        String dbName = ConfigFactory.load().getString("mongo.database");

        CassandraReadJournalSource readJournalSource = new CassandraReadJournalSource(system);
        ResumableProjection resumableProjection = new MongoResumableProjection("castle", mongoUri, dbName);

        system.actorOf(CastleProjectionActor.props(storeLycler, readJournalSource, resumableProjection));
    }
}
