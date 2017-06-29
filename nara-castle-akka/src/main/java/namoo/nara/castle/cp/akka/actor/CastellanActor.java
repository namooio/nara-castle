package namoo.nara.castle.cp.akka.actor;

import akka.persistence.AbstractPersistentActor;
import namoo.nara.castle.domain.entity.Castellan;

public class CastellanActor extends AbstractPersistentActor {
    //
    private Castellan castellan;

    @Override
    public String persistenceId() {
        //
        return castellan.getId();
    }

    @Override
    public Receive createReceiveRecover() {
        return null;
    }

    @Override
    public Receive createReceive() {
        return null;
    }
}
