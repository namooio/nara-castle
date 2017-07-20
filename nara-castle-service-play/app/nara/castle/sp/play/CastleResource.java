package nara.castle.sp.play;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.PatternsCS;
import com.fasterxml.jackson.databind.JsonNode;
import nara.castle.actor.akka.CastleSupervisorActor;
import nara.castle.actor.akka.query.CastleQueryActor;
import nara.castle.domain.castle.command.AddEnrollmentCommand;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.ModifyCastellanCommand;
import nara.castle.domain.castle.command.WithdrawMetroCommand;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castlequery.query.EnrolledCheckQuery;
import nara.castle.domain.castlequery.query.FindUnitPlateQuery;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.castle.spec.CastleService;
import nara.share.actor.akka.NaraActorConst;
import nara.share.actor.akka.result.ActorResult;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;

@Singleton
public class CastleResource extends Controller implements CastleService {
    //
    final Logger.ALogger logger = Logger.of(this.getClass());

    private ActorRef castleSupervisorActor;
    private ActorRef castleQueryActor;

    @Inject
    public CastleResource(ActorSystem system, CastleRMStoreLycler storeLycler) {
        //
        castleQueryActor = system.actorOf(CastleQueryActor.props(storeLycler));
        castleSupervisorActor = system.actorOf(CastleSupervisorActor.props(castleQueryActor));
    }

    // route mapping
    public CompletionStage<Result> buildCastle() {
        //
        JsonNode jsonNode = request().body().asJson();
        BuildCastleCommand buildCastleCommand = Json.fromJson(jsonNode, BuildCastleCommand.class);
        return buildCastle(buildCastleCommand) ;
    }

    @Override
    public CompletionStage<Result> buildCastle(BuildCastleCommand buildCastleCommand) {
        //
        return PatternsCS.ask(castleSupervisorActor, buildCastleCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResult<Castellan> result = (ActorResult) response;
            return ok(result.get().getId());
        });
    }

    // route mapping
    public CompletionStage<Result> modifyCastellan(String castellanId) {
        //
        JsonNode jsonNode = request().body().asJson();
        ModifyCastellanCommand modifyCastellanCommand = Json.fromJson(jsonNode, ModifyCastellanCommand.class);
        return modifyCastellan(castellanId, modifyCastellanCommand);
    }

    @Override
    public CompletionStage modifyCastellan(String castellanId, ModifyCastellanCommand modifyCastellanCommand) {
        //
        return PatternsCS.ask(castleSupervisorActor, modifyCastellanCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    // route mapping
    public CompletionStage<Result> addEnrollment(String castellanId) {
        //
        JsonNode jsonNode = request().body().asJson();
        AddEnrollmentCommand addEnrollmentCommand = Json.fromJson(jsonNode, AddEnrollmentCommand.class);
        return addEnrollment(castellanId, addEnrollmentCommand);
    }

    @Override
    public CompletionStage<Result> addEnrollment(String castleId, AddEnrollmentCommand addEnrollmentCommand) {
        //
        return PatternsCS.ask(castleSupervisorActor, addEnrollmentCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    // route mapping
    public CompletionStage<Result> withdrawMetro(String castellanId) {
        //
        JsonNode jsonNode = request().body().asJson();
        WithdrawMetroCommand withdrawMetroCommand = Json.fromJson(jsonNode, WithdrawMetroCommand.class);
        return withdrawMetro(castellanId, withdrawMetroCommand);
    }

    @Override
    public CompletionStage<Result> withdrawMetro(String castellanId, WithdrawMetroCommand withdrawMetroCommand) {
        //
        return PatternsCS.ask(castleSupervisorActor, withdrawMetroCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    public CompletionStage<Result> findCastellan(String castellanId) {
        //
//        FindCastleQuery query = new FindCastleQuery(castleId);
//        return PatternsCS.ask(castleSupervisorActor, query, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
//            ActorResult<Castle> result = (ActorResult) response;
//            return ok(Json.toJson(result.get()));
//        });
        return null;
    }

    public CompletionStage<Result> findCastellans() {
        //
//        FindAllCastellansQuery query = new FindAllCastellansQuery();
//        return PatternsCS.ask(castleSupervisorActor, query, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
//            ActorResult<List<Castellan>> result = (ActorResult) response;
//            return ok(Json.toJson(result.get()));
//        });
        return null;
    }

    @Override
    public CompletionStage<Result> findEnrollments(String castellanId) {
        return null;
    }

    @Override
    public CompletionStage<Result> findUnitPlate(FindUnitPlateQuery findUnitPlateQuery) {
        return null;
    }

    @Override
    public CompletionStage<Result> checkEnrolled(EnrolledCheckQuery enrolledCheckQuery) {
        return null;
    }
}
