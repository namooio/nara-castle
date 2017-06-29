package namoo.nara.castle.cp.akka.actor;

import akka.persistence.AbstractPersistentActor;
import namoo.nara.castle.domain.entity.Castle;

public class CastleActor extends AbstractPersistentActor {
    //
    private Castle castle;

    @Override
    public String persistenceId() {
        //
        return castle.getId();
    }

    @Override
    public Receive createReceiveRecover() {
        //
        return null;
    }

    @Override
    public Receive createReceive() {
        //
        return null;
    }
}
