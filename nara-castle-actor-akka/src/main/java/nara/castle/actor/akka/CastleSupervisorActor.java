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

    private ActorRef castleQueryActor;

    static public Props props(ActorRef castleQueryActor) {
        //
        return Props.create(CastleSupervisorActor.class, () -> new CastleSupervisorActor(castleQueryActor));
    }

    public CastleSupervisorActor(ActorRef castleQueryActor) {
        //
        this.castleQueryActor = castleQueryActor;
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
                String castleId = withdrawMetroCommand.getCastellanId();
                foward(castleId, Castellan.class, CastleActor.props(castleId), withdrawMetroCommand);
            })
        .onMessage(command);
    }

}
