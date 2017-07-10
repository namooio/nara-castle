package namoo.nara.castle.sp.spring.web;

import akka.actor.ActorRef;
import namoo.nara.castle.cp.spring.CastleActorLycler;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.FindAllCastlesCommand;
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
    public CastleResource(CastleActorLycler actorLycler) {
        //
        this.castleSupervisorActor = actorLycler.requestCastleSupervisorActor();
    }

    @PostMapping("castles/enrollments")
    public String enrollMetro(@RequestBody EnrollMetroCommand command) {
        //
        return new AwaitableActorExecutor<String>().execute(castleSupervisorActor, command);
    }

    @GetMapping("castles")
    public List<Castle> findCastles() {
        //
        FindAllCastlesCommand command = new FindAllCastlesCommand();
        return new AwaitableActorExecutor<List<Castle>>().execute(castleSupervisorActor, command);
    }
}
