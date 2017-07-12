package namoo.nara.castle.sp.play;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import com.fasterxml.jackson.databind.JsonNode;
import namoo.nara.castle.actor.CastleHelloActor;
import namoo.nara.castle.domain.entity.Castle;
import play.libs.F;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class CastleResource extends Controller {

    final private ActorRef castleActor;

    @Inject
    public CastleResource(ActorSystem system) {
        System.out.println(system);
        castleActor = system.actorOf(CastleHelloActor.props());
    }


    public Result findAllCastles() {
        //
        List<Castle> castles = new ArrayList<>();
        castles.add(getCastle("01"));

        JsonNode castlesJson = Json.toJson(castles);
        return ok(castlesJson);
    }

    public Result findCastle(String castleId) {
        //
        Castle castle = getCastle(castleId);

        JsonNode castleJson = Json.toJson(castle);
        return ok(castleJson);
    }

    public F.Promise<Result> findCastleByEmail(String email) {
        return test(email);
    }


    public F.Promise<Result> test(String param) {
        //
        System.out.println("Start request: " + Thread.currentThread().getName());

        return F.Promise.wrap(Patterns.ask(castleActor, param, 1000))
                .map(response -> {
                    System.out.println("Receive response at controller: " + Thread.currentThread().getName());
                    return ok((String) response);
                });
    }

    private Castle getCastle(String castleId) {
        Castle castle = new Castle();
        castle.setPrimaryEmail(castleId + "@namoo.io");
        castle.setName("Castle name");
        castle.setBuiltTime(System.currentTimeMillis());

        return castle;
    }

}
