package namoo.nara.castle.sp.spring.web;

import akka.actor.ActorRef;
import namoo.nara.castle.cp.spring.CastleAkkaActorSpringLycler;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.query.castle.FindAllCastlesQuery;
import namoo.nara.share.akka.support.util.AwaitableActorExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("castle-api")
public class CastleResource implements CastleService {
    //
    private ActorRef castleSupervisorActor;

    @Autowired
    public CastleResource(CastleAkkaActorSpringLycler actorLycler) {
        //
        this.castleSupervisorActor = actorLycler.requestCastleSupervisorActor();
    }

    @PostMapping("castles")
    public String buildCastle(
            @RequestBody BuildCastleCommand command
    ) {
        //
        return new AwaitableActorExecutor<String>().execute(castleSupervisorActor, command);
    }

    @PostMapping("castles/{castleId}/enrollments")
    public void enrollMetro(
            @PathVariable("castleId") String castleId,
            @RequestBody EnrollMetroCommand command
    ) {
        //
        this.castleSupervisorActor.tell(command, ActorRef.noSender());
    }

    @GetMapping("castles")
    public List<Castle> findCastles() {
        //
        FindAllCastlesQuery command = new FindAllCastlesQuery();
        return new AwaitableActorExecutor<List<Castle>>().execute(castleSupervisorActor, command);
    }
}
