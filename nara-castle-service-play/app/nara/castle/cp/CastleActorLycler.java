package nara.castle.cp;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import nara.castle.actor.akka.CastleSupervisorActor;
import nara.castle.actor.akka.projection.CastleProjectionActor;
import nara.castle.actor.akka.query.CastleQueryActor;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.share.actor.akka.projection.journal.cassandra.CassandraReadJournalSource;
import nara.share.actor.akka.projection.resume.ResumableProjection;
import nara.share.actor.akka.projection.resume.mongo.MongoResumableProjection;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CastleActorLycler {
    //
    private ActorSystem actorSystem;

    private ActorRef castleSupervisorActor;
    private ActorRef castleQueryActor;

    @Inject
    public CastleActorLycler(ActorSystem actorSystem, CastleRMStoreLycler castleRMStoreLycler) {
        //
        this.actorSystem = actorSystem;

        castleQueryActor = actorSystem.actorOf(CastleQueryActor.props(castleRMStoreLycler), "castle-query");
        castleSupervisorActor = actorSystem.actorOf(CastleSupervisorActor.props(castleQueryActor), "castle-supervisor");

        String mongoUri = ConfigFactory.load().getString("mongo.uri");
        String dbName = ConfigFactory.load().getString("mongo.database");

        CassandraReadJournalSource readJournalSource = new CassandraReadJournalSource(actorSystem);
        ResumableProjection resumableProjection = new MongoResumableProjection("castle", mongoUri, dbName);
        actorSystem.actorOf(CastleProjectionActor.props(castleRMStoreLycler, readJournalSource, resumableProjection));
    }

    public ActorRef requestCastleQueryActor() {
        //
        return castleQueryActor;
    }

    public ActorRef requestCastleSupervisorActor() {
        //
        return castleSupervisorActor;
    }
}
