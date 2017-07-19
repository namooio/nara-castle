package nara.castle.akka.projection;

import akka.actor.Props;
import nara.share.akka.support.actor.NaraProjectionActor;
import nara.share.akka.support.projection.resume.ResumableProjection;
import nara.share.akka.support.projection.ViewBuilder;
import nara.castle.akka.projection.castellan.CastellanViewBuilder;
import nara.castle.akka.projection.castle.CastleViewBuilder;
import nara.castle.akka.projection.castle.MetroEnrolledViewBuilder;
import nara.castle.domain.castle.event.CastellanCreated;
import nara.castle.domain.castle.event.CastleBuilt;
import nara.castle.domain.castle.event.MetroEnrolled;
import nara.castle.domain.castlequery.store.CastleViewStoreLycler;

import java.util.Map;

public class CastleProjectionActor extends NaraProjectionActor {
    //
    private CastleViewStoreLycler storeLycler;

    static public Props props(CastleViewStoreLycler storeLycler, ResumableProjection resumableProjection) {
        //
        return Props.create(CastleProjectionActor.class, () -> new CastleProjectionActor(storeLycler, resumableProjection));
    }

    public CastleProjectionActor(CastleViewStoreLycler storeLycler, ResumableProjection resumableProjection) {
        //
        super("castle-event", resumableProjection);
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
