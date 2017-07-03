package namoo.nara.castle.cp.akka.actor.store;

import akka.actor.AbstractActor;
import akka.actor.Props;
import namoo.nara.castle.domain.store.CastellanStore;

public class CastellanStoreActor extends AbstractActor {
    //
    private CastellanStore castellanStore;

    static public Props props(CastellanStore castellanStore) {
        //
        return Props.create(CastellanStoreActor.class, () -> new CastellanStoreActor(castellanStore));
    }

    public CastellanStoreActor(CastellanStore castellanStore) {
        //
        this.castellanStore = castellanStore;
    }

    @Override
    public Receive createReceive() {
        //
        return null;
    }
}
