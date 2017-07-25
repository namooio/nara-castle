package nara.castle.actor.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import nara.castle.actor.akka.command.CastleActor;
import nara.castle.domain.castle.command.*;
import nara.castle.domain.castle.entity.Castellan;
import nara.share.actor.akka.NaraActor;
import nara.share.domain.protocol.NaraCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleSupervisorActor extends NaraActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private ActorRef castleQuery;

    static public Props props(ActorRef castleQuery) {
        //
        return Props.create(CastleSupervisorActor.class, () -> new CastleSupervisorActor(castleQuery));
    }

    public CastleSupervisorActor(ActorRef castleQuery) {
        //
        this.castleQuery = castleQuery;
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        matcher()
        .match(BuildCastleCommand.class, buildCastleCommand -> {
            //
            Castellan castellan = new Castellan(buildCastleCommand.getEnrollment());
            foward(castellan.getId(), Castellan.class, CastleActor.props(castellan), buildCastleCommand);
        })
        .match(ModifyCastellanCommand.class, modifyCastellanCommand -> {
            //
            String castellanId = modifyCastellanCommand.getCastellanId();
            foward(castellanId, Castellan.class, CastleActor.props(castellanId), modifyCastellanCommand);
        })
        .match(DemolishCastleCommand.class, demolishCastleCommand -> {
            //
            String castellanId = demolishCastleCommand.getCastellanId();
            foward(castellanId, Castellan.class, CastleActor.props(castellanId), demolishCastleCommand);
        })
        .match(AddEnrollmentCommand.class, addEnrollmentCommand -> {
            //
            String castellanId = addEnrollmentCommand.getCastellanId();
            foward(castellanId, Castellan.class, CastleActor.props(castellanId), addEnrollmentCommand);
        })
        .match(WithdrawMetroCommand.class, withdrawMetroCommand -> {
            //
            String castellanId = withdrawMetroCommand.getCastellanId();
            foward(castellanId, Castellan.class, CastleActor.props(castellanId), withdrawMetroCommand);
        })
        .onMessage(command);
    }

}
