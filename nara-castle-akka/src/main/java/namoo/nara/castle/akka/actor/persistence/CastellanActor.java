package namoo.nara.castle.akka.actor.persistence;

import akka.actor.Props;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.IdentityPlate;
import namoo.nara.castle.domain.spec.command.castellan.ModifyCastellanCommand;
import namoo.nara.castle.domain.spec.command.castellan.RegisterCastellanCommand;
import namoo.nara.castle.domain.spec.event.castellan.CastellanCreated;
import namoo.nara.castle.domain.spec.event.castellan.CastellanModified;
import namoo.nara.castle.domain.spec.query.castellan.FindIdentityPlateQuery;
import namoo.nara.share.akka.support.actor.NaraPersistentActor;
import namoo.nara.share.domain.event.NaraEvent;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.domain.protocol.NaraQuery;

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
