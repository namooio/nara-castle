package nara.castle.sp.play;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.PatternsCS;
import com.fasterxml.jackson.databind.JsonNode;
import nara.castle.actor.akka.CastleSupervisorActor;
import nara.castle.actor.akka.query.CastleQueryActor;
import nara.castle.domain.castle.command.*;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import nara.castle.domain.castlequery.query.*;
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
import java.util.List;
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

    @Override
    public CompletionStage<Result> demolishCastle(String castellanId) {
        //
        DemolishCastleCommand demolishCastleCommand = new DemolishCastleCommand(castellanId);
        return PatternsCS.ask(castleSupervisorActor, demolishCastleCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    public CompletionStage<Result> findCastellan(String castellanId) {
        //
        FindCastellanQuery findCastellanQuery = new FindCastellanQuery(castellanId);
        return PatternsCS.ask(castleQueryActor, findCastellanQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResult<CastellanRM> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }

    public CompletionStage<Result> findCastellans() {
        //
        FindCastellansQuery findCastellansQuery = new FindCastellansQuery();
        return PatternsCS.ask(castleQueryActor, findCastellansQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResult<List<CastellanRM>> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }

    @Override
    public CompletionStage<Result> existsCastellan(String castellanId) {
        //
        ExistenceCheckQuery existenceCheckQuery = new ExistenceCheckQuery(castellanId);
        return PatternsCS.ask(castleQueryActor, existenceCheckQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResult<Boolean> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }

    @Override
    public CompletionStage<Result> findEnrollments(String castellanId) {
        //
        FindEnrollmentsQuery findEnrollmentsQuery = new FindEnrollmentsQuery(castellanId);
        return PatternsCS.ask(castleQueryActor, findEnrollmentsQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResult<List<EnrollmentRM>> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }

    // route mapping
    public CompletionStage<Result> findUnitPlates() {
        //
        KeyAttr keyAttr = KeyAttr.valueOf(request().getQueryString("keyAttr"));
        String keyValue = request().getQueryString("keyValue");

        return findUnitPlates(keyAttr, keyValue);
    }

    @Override
    public CompletionStage<Result> findUnitPlates(KeyAttr keyAttr, String keyValue) {
        //
        FindUnitPlatesQuery findUnitPlatesQuery = new FindUnitPlatesQuery(keyAttr, keyValue);
        return PatternsCS.ask(castleQueryActor, findUnitPlatesQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResult<List<UnitPlateRM>> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }

    @Override
    public CompletionStage<Result> checkEnrolled(String castellanId, String metroId) {
        //
        EnrolledCheckQuery enrolledCheckQuery = new EnrolledCheckQuery(castellanId, metroId);
        return PatternsCS.ask(castleQueryActor, enrolledCheckQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResult<Boolean> result = (ActorResult) response;
            return ok(Json.toJson(result.get()));
        });
    }
}
