package nara.castle.akka.projection;

import akka.actor.Props;
import nara.castle.akka.projection.castellan.CastellanViewBuilder;
import nara.castle.akka.projection.castle.CastleViewBuilder;
import nara.castle.akka.projection.castle.MetroEnrolledViewBuilder;
import nara.castle.domain.castle.event.CastellanCreated;
import nara.castle.domain.castle.event.CastleBuilt;
import nara.castle.domain.castle.event.MetroEnrolled;
import nara.castle.domain.castlequery.store.CastleViewStoreLycler;
import nara.share.akka.support.actor.NaraProjectionActor;
import nara.share.akka.support.projection.ViewBuilder;
import nara.share.akka.support.projection.journal.ReadJournalSource;
import nara.share.akka.support.projection.resume.ResumableProjection;

import java.util.Map;

public class CastleProjectionActor extends NaraProjectionActor {
    //
    private CastleViewStoreLycler storeLycler;

    static public Props props(
            CastleViewStoreLycler storeLycler,
            ReadJournalSource readJournalSource,
            ResumableProjection resumableProjection
    ) {
        //
        return Props.create(CastleProjectionActor.class, () -> new CastleProjectionActor(storeLycler, readJournalSource, resumableProjection));
    }

    public CastleProjectionActor(CastleViewStoreLycler storeLycler, ReadJournalSource readJournalSource, ResumableProjection resumableProjection) {
        //
        super("castle", readJournalSource, resumableProjection);
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
