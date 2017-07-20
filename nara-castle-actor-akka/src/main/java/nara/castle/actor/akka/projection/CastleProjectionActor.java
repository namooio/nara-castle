package nara.castle.actor.akka.projection;

import akka.actor.Props;
import nara.castle.domain.castlequery.store.CastleViewStoreLycler;
import nara.share.actor.akka.NaraProjectionActor;
import nara.share.actor.akka.projection.ViewBuilder;
import nara.share.actor.akka.projection.journal.ReadJournalSource;
import nara.share.actor.akka.projection.resume.ResumableProjection;

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
//        viewBuilderMap.put(CastleBuilt.class.getName(), new CastleViewBuilder(storeLycler.requestCastleViewStore()));
//
//        viewBuilderMap.put(MetroEnrolled.class.getName(), new MetroEnrolledViewBuilder(storeLycler.requestCastleViewStore()));
    }
}
