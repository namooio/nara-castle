package namoo.nara.castle.akka.projection;

import akka.actor.Props;
import namoo.nara.castle.akka.projection.castellan.CastellanCreatedViewProjector;
import namoo.nara.castle.akka.projection.castle.CastleBuiltViewProjector;
import namoo.nara.castle.akka.projection.castle.MetroEnrolledViewProjector;
import namoo.nara.castle.domain.spec.event.castellan.CastellanCreated;
import namoo.nara.castle.domain.spec.event.castle.CastleBuilt;
import namoo.nara.castle.domain.spec.event.castle.MetroEnrolled;
import namoo.nara.castle.domain.view.store.CastleViewStoreLycler;
import namoo.nara.share.akka.support.actor.NaraProjectionActor;

public class CastleProjectionActor extends NaraProjectionActor {
    //
    static public Props props(CastleViewStoreLycler storeLycler) {
        //
        return Props.create(CastleProjectionActor.class, () -> new CastleProjectionActor(storeLycler));
    }

    public CastleProjectionActor(CastleViewStoreLycler storeLycler) {
        //
        super("castle-event");

        addViewProjector(CastleBuilt.class.getName(), new CastleBuiltViewProjector(storeLycler.requestCastleViewStore()));
        addViewProjector(MetroEnrolled.class.getName(), new MetroEnrolledViewProjector(storeLycler.requestCastleViewStore()));

        addViewProjector(CastellanCreated.class.getName(), new CastellanCreatedViewProjector(storeLycler.requestCastellanViewStore()));
    }
}
