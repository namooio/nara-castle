package nara.castle.sp.play;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.PatternsCS;
import com.fasterxml.jackson.databind.JsonNode;
import namoo.nara.share.akka.support.actor.NaraActorConst;
import namoo.nara.share.akka.support.actor.result.ActorResult;
import nara.castle.akka.actor.CastleSupervisorActor;
import nara.castle.domain.entity.Castellan;
import nara.castle.domain.entity.Castle;
import nara.castle.domain.spec.CastleService;
import nara.castle.domain.spec.command.castle.BuildCastleCommand;
import nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import nara.castle.domain.spec.query.castellan.FindAllCastellansQuery;
import nara.castle.domain.spec.query.castle.FindAllCastlesQuery;
import nara.castle.domain.spec.query.castle.FindCastleQuery;
import nara.castle.domain.view.store.CastleViewStoreLycler;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.CompletionStage;

@Singleton
public class CastleResource extends Controller implements CastleService {
    //
    final Logger.ALogger logger = Logger.of(this.getClass());

    private ActorRef castleSupervisorActor;

    @Inject
    public CastleResource(ActorSystem system, CastleViewStoreLycler storeLycler) {
        //
        castleSupervisorActor = system.actorOf(CastleSupervisorActor.props(storeLycler));
    }

    // route mapping
    public CompletionStage<Result> buildCastle() {
        //
        JsonNode jsonNode = request().body().asJson();
        BuildCastleCommand command = Json.fromJson(jsonNode, BuildCastleCommand.class);
        return buildCastle(command) ;
    }

    public CompletionStage<Result> buildCastle(BuildCastleCommand command) {
        //
        return PatternsCS.ask(castleSupervisorActor, command, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResult<Castle> result = (ActorResult) response;
            return ok(result.get().getId());
        });
    }

    // route mapping
    public CompletionStage<Result> enrollMetro(String castleId) {
        //
        JsonNode jsonNode = request().body().asJson();
        EnrollMetroCommand command = Json.fromJson(jsonNode, EnrollMetroCommand.class);
        return enrollMetro(castleId, command);
    }

    public CompletionStage<Result> enrollMetro(String castleId, EnrollMetroCommand command) {
        //
        return PatternsCS.ask(castleSupervisorActor, command, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    public CompletionStage<Result> findCastle(String castleId) {
        //
        FindCastleQuery query = new FindCastleQuery(castleId);
        return PatternsCS.ask(castleSupervisorActor, query, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResult<Castle> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }

    public CompletionStage<Result> findCastles() {
        //
        FindAllCastlesQuery query = new FindAllCastlesQuery();
        return PatternsCS.ask(castleSupervisorActor, query, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResult<List<Castle>> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }

    public CompletionStage<Result> findCastellans() {
        //
        FindAllCastellansQuery query = new FindAllCastellansQuery();
        return PatternsCS.ask(castleSupervisorActor, query, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResult<List<Castellan>> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }

}
