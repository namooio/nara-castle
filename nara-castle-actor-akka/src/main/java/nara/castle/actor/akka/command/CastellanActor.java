package nara.castle.actor.akka.command;

import akka.actor.Props;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castlequery.model.IdentityPlate;
import nara.castle.domain.castle.command.ModifyCastellanCommand;
import nara.castle.domain.castle.command.RegisterCastellanCommand;
import nara.castle.domain.castle.event.CastellanCreated;
import nara.castle.domain.castle.event.CastellanModified;
import nara.castle.domain.castlequery.query.FindIdentityPlateQuery;
import nara.share.actor.akka.NaraPersistentActor;
import nara.share.domain.event.NaraEvent;
import nara.share.domain.protocol.NaraCommand;
import nara.share.domain.protocol.NaraQuery;

public class CastellanActor extends NaraPersistentActor<Castellan> {
    //
    static public Props props(String castellanId) {
        //
        return Props.create(CastellanActor.class, () -> new CastellanActor(castellanId));
    }

    public CastellanActor(String castellanId) {
        //
        super(new Castellan(castellanId));
    }

    @Override
    public void handleEvent(NaraEvent event) {
        //
        matcher()
            .match(CastellanCreated.class, castellanCreated -> getState().apply(castellanCreated))
            .match(CastellanModified.class, castellanModified -> getState().apply(castellanModified))
        .onMessage(event);
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        matcher()
            .match(RegisterCastellanCommand.class, registerCastellanCommand -> {
                //
                Castellan castellan = new Castellan(registerCastellanCommand.getCastle());
                persist(new CastellanCreated(castellan), this::handleAndRespond);
            })
            .match(ModifyCastellanCommand.class, modifyCastellanCommand -> {
                //
                persist(new CastellanModified(modifyCastellanCommand), this::handleAndRespond);
            })
        .onMessage(command);
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        matcher()
            .match(FindIdentityPlateQuery.class, findIdentityPlateQuery -> {
                //
                IdentityPlate identityPlate = new IdentityPlate(getState());
                responseResult(identityPlate);
            })
        .onMessage(query);
    }

}
