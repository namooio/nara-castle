package namoo.nara.castle.sp.play;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.PatternsCS;
import com.fasterxml.jackson.databind.JsonNode;
import namoo.nara.castle.akka.actor.CastleSupervisorActor;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.query.castellan.FindAllCastellansQuery;
import namoo.nara.castle.domain.spec.query.castle.FindAllCastlesQuery;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import namoo.nara.castle.domain.view.store.CastleViewStoreLycler;
import namoo.nara.share.akka.support.actor.result.ActorResult;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.CompletionStage;

@Singleton
public class CastleResource extends Controller {
    //
    final Logger.ALogger logger = Logger.of(this.getClass());

    private ActorRef castleSupervisorActor;

    @Inject
    public CastleResource(ActorSystem system, CastleViewStoreLycler storeLycler) {
        //
        castleSupervisorActor = system.actorOf(CastleSupervisorActor.props(storeLycler));
    }

    public CompletionStage<Result> buildCastle() {
        //
        JsonNode jsonNode = request().body().asJson();
        BuildCastleCommand command = Json.fromJson(jsonNode, BuildCastleCommand.class);
        return PatternsCS.ask(castleSupervisorActor, command, 1000).thenApply(response -> {
            ActorResult<Castle> result = (ActorResult) response;
            return ok(result.get().getId());
        });
    }

    public CompletionStage<Result> enrollMetro(String castleId) {
        //
        JsonNode jsonNode = request().body().asJson();
        EnrollMetroCommand command = Json.fromJson(jsonNode, EnrollMetroCommand.class);
        return PatternsCS.ask(castleSupervisorActor, command, 1000).thenApply(response -> ok());
    }

    public CompletionStage<Result> findCastle(String castleId) {
        //
        FindCastleQuery query = new FindCastleQuery(castleId);
        return PatternsCS.ask(castleSupervisorActor, query, 1000).thenApply(response -> {
            ActorResult<Castle> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }

    public CompletionStage<Result> findCastles() {
        //
        FindAllCastlesQuery query = new FindAllCastlesQuery();
        return PatternsCS.ask(castleSupervisorActor, query, 1000).thenApply(response -> {
            ActorResult<List<Castle>> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }

    public CompletionStage<Result> findCastellans() {
        //
        FindAllCastellansQuery query = new FindAllCastellansQuery();
        return PatternsCS.ask(castleSupervisorActor, query, 1000).thenApply(response -> {
            ActorResult<List<Castellan>> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }

}
