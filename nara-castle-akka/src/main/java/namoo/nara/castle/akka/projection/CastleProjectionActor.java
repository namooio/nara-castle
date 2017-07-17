package namoo.nara.castle.akka.projection;

import akka.actor.Props;
import namoo.nara.castle.akka.projection.castellan.CastellanViewBuilder;
import namoo.nara.castle.akka.projection.castle.CastleViewBuilder;
import namoo.nara.castle.akka.projection.castle.MetroEnrolledViewBuilder;
import namoo.nara.castle.domain.spec.event.castellan.CastellanCreated;
import namoo.nara.castle.domain.spec.event.castle.CastleBuilt;
import namoo.nara.castle.domain.spec.event.castle.MetroEnrolled;
import namoo.nara.castle.domain.view.store.CastleViewStoreLycler;
import namoo.nara.share.akka.support.actor.NaraProjectionActor;
import namoo.nara.share.akka.support.projection.ViewBuilder;

import java.util.Map;

public class CastleProjectionActor extends NaraProjectionActor {
    //
    private CastleViewStoreLycler storeLycler;

    static public Props props(CastleViewStoreLycler storeLycler) {
        //
        return Props.create(CastleProjectionActor.class, () -> new CastleProjectionActor(storeLycler));
    }

    public CastleProjectionActor(CastleViewStoreLycler storeLycler) {
        //
        super("castle-event");
        this.storeLycler = storeLycler;
    }

    @Override
    protected void configProjection(Map<String, ViewBuilder> viewBuilderMap) {
        //
        viewBuilderMap.put(CastleBuilt.class.getName(), new CastleViewBuilder(storeLycler.requestCastleViewStore()));
        viewBuilderMap.put(MetroEnrolled.class.getName(), new MetroEnrolledViewBuilder(storeLycler.requestCastleViewStore()));
        viewBuilderMap.put(CastellanCreated.class.getName(), new CastellanViewBuilder(storeLycler.requestCastellanViewStore()));
    }
}
