package nara.castle.actor.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import nara.castle.actor.akka.query.CastleQueryActor;
import nara.castle.da.mongo.CastleRMMongoStoreLycler;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;

import java.io.IOException;

public class CastleTestApplication {
    //
    public static void main(String[] args) throws IOException {
        //
        MongodStarter starter = MongodStarter.getDefaultInstance();

        String bindIp = "127.0.0.1";
        int port = 55555;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .build();

        MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();

        ActorSystem actorSystem = ActorSystem.create("nara");

        CastleRMStoreLycler rmStoreLycler = new CastleRMMongoStoreLycler("mongodb://localhost:55555/nara_castle", "nara_castle");

        ActorRef castleQuery = actorSystem.actorOf(CastleQueryActor.props(rmStoreLycler), "castle-query");
        actorSystem.actorOf(CastleSupervisorActor.props(castleQuery), "castle-supervisor");
    }
}
