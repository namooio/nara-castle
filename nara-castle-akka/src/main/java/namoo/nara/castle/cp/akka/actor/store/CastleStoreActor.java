package namoo.nara.castle.cp.akka.actor.store;

import akka.actor.AbstractActor;
import akka.actor.Props;
import namoo.nara.castle.domain.store.CastleStore;

public class CastleStoreActor extends AbstractActor {
    //
    private CastleStore castleStore;

    static public Props props(CastleStore castleStore) {
        //
        return Props.create(CastleStoreActor.class, () -> new CastleStoreActor(castleStore));
    }

    public CastleStoreActor(CastleStore castleStore) {
        //
        this.castleStore = castleStore;
    }

    @Override
    public Receive createReceive() {
        //
        return null;
    }
}
