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
import nara.share.domain.OffsetList;
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
    private ActorRef castleSupervisorActor;
    private ActorRef castleQueryActor;

    @Inject
    public CastleResource(CastleActorLycler actorLycler) {
        //
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
        modifyCastellanCommand.setCastellanId(castellanId);
        return modifyCastellan(modifyCastellanCommand);
    }

    @Override
    public CompletionStage modifyCastellan(ModifyCastellanCommand modifyCastellanCommand) {
        //
        return PatternsCS.ask(castleSupervisorActor, modifyCastellanCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    // route mapping
    public CompletionStage<Result> addEnrollment(String castellanId) {
        //
        JsonNode jsonNode = request().body().asJson();
        AddEnrollmentCommand addEnrollmentCommand = Json.fromJson(jsonNode, AddEnrollmentCommand.class);
        addEnrollmentCommand.setCastellanId(castellanId);
        return addEnrollment(addEnrollmentCommand);
    }

    @Override
    public CompletionStage<Result> addEnrollment(AddEnrollmentCommand addEnrollmentCommand) {
        //
        return PatternsCS.ask(castleSupervisorActor, addEnrollmentCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    // route mapping
    public CompletionStage<Result> withdrawMetro(String castellanId) {
        //
        JsonNode jsonNode = request().body().asJson();
        WithdrawMetroCommand withdrawMetroCommand = Json.fromJson(jsonNode, WithdrawMetroCommand.class);
        withdrawMetroCommand.setCastellanId(castellanId);
        return withdrawMetro(withdrawMetroCommand);
    }

    @Override
    public CompletionStage<Result> withdrawMetro(WithdrawMetroCommand withdrawMetroCommand) {
        //
        return PatternsCS.ask(castleSupervisorActor, withdrawMetroCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    // route mapping
    public CompletionStage<Result> demolishCastle(String castleId) {
        //
        return demolishCastle(new DemolishCastleCommand(castleId));
    }

    @Override
    public CompletionStage<Result> demolishCastle(DemolishCastleCommand demolishCastleCommand) {
        //
        return PatternsCS.ask(castleSupervisorActor, demolishCastleCommand, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> ok());
    }

    // route mapping
    public CompletionStage<Result> findCastellan(String castellanId) {
        //
        FindCastellanQuery findCastellanQuery = new FindCastellanQuery(castellanId);
        return findCastellan(findCastellanQuery);
    }

    @Override
    public CompletionStage<Result> findCastellan(FindCastellanQuery findCastellanQuery) {
        //
        return PatternsCS.ask(castleQueryActor, findCastellanQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<CastellanRM> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }

    // route mapping
    public CompletionStage<Result> findCastellans() {
        //
        String keyAttrStr = request().getQueryString("keyAttr");
        String keyValue = request().getQueryString("keyValue");
        String lastCastellanId = request().getQueryString("lastCastellanId");
        String limitStr = request().getQueryString("limit");

        KeyAttr keyAttr = keyAttrStr != null ? KeyAttr.valueOf(keyAttrStr) : null;
        int limit = limitStr != null ? Integer.parseInt(limitStr) : Integer.MAX_VALUE;

        return findCastellans(new FindCastellansQuery(keyAttr, keyValue, lastCastellanId, limit));
    }

    @Override
    public CompletionStage<Result> findCastellans(FindCastellansQuery findCastellansQuery) {
        //
        return PatternsCS.ask(castleQueryActor, findCastellansQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<List<CastellanRM>> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }

    // route mapping
    public CompletionStage<Result> findCastellansPage() {
        //
        String pageStr = request().getQueryString("page");
        String limitStr = request().getQueryString("limit");
        int page = pageStr != null ? Integer.parseInt(pageStr) : 0;
        int limit = limitStr != null ? Integer.parseInt(limitStr) : Integer.MAX_VALUE;

        return findCastellansPage(new FindCastellansPageQuery(page, limit));
    }

    @Override
    public CompletionStage<Result> findCastellansPage(FindCastellansPageQuery findCastellansPageQuery) {
        //
        return PatternsCS.ask(castleQueryActor, findCastellansPageQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<OffsetList<CastellanRM>> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }

    // route mapping
    public CompletionStage<Result> existsCastellan(String castellanId) {
        //
        return existsCastellan(new ExistenceCheckQuery(castellanId));
    }

    @Override
    public CompletionStage existsCastellan(ExistenceCheckQuery existenceCheckQuery) {
        //
        return PatternsCS.ask(castleQueryActor, existenceCheckQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<Boolean> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }

    // route mapping
    public CompletionStage<Result> findEnrollments(String castellanId) {
        //
        return findEnrollments(new FindEnrollmentsQuery(castellanId));
    }

    @Override
    public CompletionStage<Result> findEnrollments(FindEnrollmentsQuery findEnrollmentsQuery) {
        //
        return PatternsCS.ask(castleQueryActor, findEnrollmentsQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<List<EnrollmentRM>> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }

    // route mapping
    public CompletionStage<Result> findUnitPlates() {
        //
        String keyAttrStr = request().getQueryString("keyAttr");
        String keyValue = request().getQueryString("keyValue");
        String limitStr = request().getQueryString("limit");

        KeyAttr keyAttr = keyAttrStr != null ? KeyAttr.valueOf(keyAttrStr) : null;
        int limit = limitStr != null ? Integer.parseInt(limitStr) : Integer.MAX_VALUE;

        FindUnitPlatesQuery findUnitPlatesQuery = new FindUnitPlatesQuery(keyAttr, keyValue, limit);

        return findUnitPlates(findUnitPlatesQuery);
    }

    @Override
    public CompletionStage findUnitPlates(FindUnitPlatesQuery findUnitPlatesQuery) {
        //
        return PatternsCS.ask(castleQueryActor, findUnitPlatesQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<List<UnitPlateRM>> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }

    // route mapping
    public CompletionStage<Result> checkEnrolled(String castellanId, String metroId) {
        //
        return checkEnrolled(new EnrolledCheckQuery(castellanId, metroId));
    }

    @Override
    public CompletionStage checkEnrolled(EnrolledCheckQuery enrolledCheckQuery) {
        //
        return PatternsCS.ask(castleQueryActor, enrolledCheckQuery, NaraActorConst.DEFAULT_TIMEOUT).thenApply(response -> {
            ActorResponse<Boolean> result = (ActorResponse) response;
            return ok(Json.toJson(result.get()));
        });
    }
}
