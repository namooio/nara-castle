package namoo.nara.castle.sp.spring.web;

import akka.actor.ActorRef;
import namoo.nara.castle.cp.spring.CastleAkkaActorSpringLycler;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.query.castellan.FindAllCastellansQuery;
import namoo.nara.castle.domain.spec.query.castle.FindAllCastlesQuery;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import namoo.nara.castle.domain.view.CastellanView;
import namoo.nara.castle.domain.view.CastleView;
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

    @Override
    @PostMapping("castles")
    public String buildCastle(
            @RequestBody BuildCastleCommand command
    ) {
        //
        return new AwaitableActorExecutor<String>().execute(castleSupervisorActor, command);
    }

    @Override
    @PostMapping("castles/{castleId}/enrollments")
    public void enrollMetro(
            @PathVariable("castleId") String castleId,
            @RequestBody EnrollMetroCommand command
    ) {
        //
        this.castleSupervisorActor.tell(command, ActorRef.noSender());
    }

    @Override
    @GetMapping("castles/{castleId}")
    public Castle findCastle(
            @PathVariable("castleId") String castleId
    ) {
        //
        return new AwaitableActorExecutor<Castle>().execute(castleSupervisorActor, new FindCastleQuery(castleId));
    }

    @Override
    @GetMapping("castles")
    public List<CastleView> findCastles() {
        //
        FindAllCastlesQuery query = new FindAllCastlesQuery();
        return new AwaitableActorExecutor<List<CastleView>>().execute(castleSupervisorActor, query);
    }

    @Override
    @GetMapping("castellans")
    public List<CastellanView> findCastellans() {
        //
        FindAllCastellansQuery query = new FindAllCastellansQuery();
        return new AwaitableActorExecutor<List<CastellanView>>().execute(castleSupervisorActor, query);
    }
}
