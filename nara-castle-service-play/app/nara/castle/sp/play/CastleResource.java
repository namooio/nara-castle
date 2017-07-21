package nara.castle.sp.play;

import akka.actor.ActorRef;
import akka.pattern.PatternsCS;
import com.fasterxml.jackson.databind.JsonNode;
import nara.castle.cp.CastleActorLycler;
import nara.castle.domain.castle.command.*;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import nara.castle.domain.castlequery.query.*;
import nara.castle.spec.CastleService;
import nara.share.actor.akka.NaraActorConst;
import nara.share.actor.akka.message.ActorResponse;
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
    private CastleActorLycler actorLycler;

    private ActorRef castleSupervisorActor;
    private ActorRef castleQueryActor;

    @Inject
    public CastleResource(CastleActorLycler actorLycler) {
        //
        this.actorLycler = actorLycler;
        this.castleSupervisorActor = actorLycler.requestCastleSupervisorActor();
        this.castleQueryActor = actorLycler.requestCastleQueryActor();
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
            ActorResponse<Castellan> result = (ActorResponse) response;
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
        ActorRef castleActor = actorLycler.requestCastleActor(castellanId);
        return PatternsCS.ask(castleActor, modifyCastellanCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
//        return PatternsCS.ask(castleSupervisorActor, modifyCastellanCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    // route mapping
    public CompletionStage<Result> addEnrollment(String castellanId) {
        //
        JsonNode jsonNode = request().body().asJson();
        AddEnrollmentCommand addEnrollmentCommand = Json.fromJson(jsonNode, AddEnrollmentCommand.class);
        return addEnrollment(castellanId, addEnrollmentCommand);
    }

    @Override
    public CompletionStage<Result> addEnrollment(String castellanId, AddEnrollmentCommand addEnrollmentCommand) {
        //
        ActorRef castleActor = actorLycler.requestCastleActor(castellanId);
        return PatternsCS.ask(castleActor, addEnrollmentCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
//        return PatternsCS.ask(castleSupervisorActor, addEnrollmentCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
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
        ActorRef castleActor = actorLycler.requestCastleActor(castellanId);
        return PatternsCS.ask(castleActor, withdrawMetroCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
//        return PatternsCS.ask(castleSupervisorActor, withdrawMetroCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    @Override
    public CompletionStage<Result> demolishCastle(String castellanId) {
        //
        ActorRef castleActor = actorLycler.requestCastleActor(castellanId);
        DemolishCastleCommand demolishCastleCommand = new DemolishCastleCommand();
        return PatternsCS.ask(castleActor, demolishCastleCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
//        return PatternsCS.ask(castleSupervisorActor, demolishCastleCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    public CompletionStage<Result> findCastellan(String castellanId) {
        //
        FindCastellanQuery findCastellanQuery = new FindCastellanQuery(castellanId);
        return PatternsCS.ask(castleQueryActor, findCastellanQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<CastellanRM> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }

    public CompletionStage<Result> findCastellans() {
        //
        FindCastellansQuery findCastellansQuery = new FindCastellansQuery();
        return PatternsCS.ask(castleQueryActor, findCastellansQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<List<CastellanRM>> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }

    @Override
    public CompletionStage<Result> existsCastellan(String castellanId) {
        //
        ExistenceCheckQuery existenceCheckQuery = new ExistenceCheckQuery(castellanId);
        return PatternsCS.ask(castleQueryActor, existenceCheckQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<Boolean> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }

    @Override
    public CompletionStage<Result> findEnrollments(String castellanId) {
        //
        FindEnrollmentsQuery findEnrollmentsQuery = new FindEnrollmentsQuery(castellanId);
        return PatternsCS.ask(castleQueryActor, findEnrollmentsQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<List<EnrollmentRM>> result = (ActorResponse) response;
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
            ActorResponse<List<UnitPlateRM>> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }

    @Override
    public CompletionStage<Result> checkEnrolled(String castellanId, String metroId) {
        //
        EnrolledCheckQuery enrolledCheckQuery = new EnrolledCheckQuery(castellanId, metroId);
        return PatternsCS.ask(castleQueryActor, enrolledCheckQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<Boolean> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }
}
