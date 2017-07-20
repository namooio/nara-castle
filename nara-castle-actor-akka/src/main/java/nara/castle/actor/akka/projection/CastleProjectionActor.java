package nara.castle.actor.akka.projection;

import akka.actor.Props;
import nara.castle.actor.akka.projection.builder.CastellanRMBuilder;
import nara.castle.actor.akka.projection.builder.EnrollmentRMBuilder;
import nara.castle.actor.akka.projection.builder.UnitPlateRMBuilder;
import nara.castle.domain.castle.event.CastellanModified;
import nara.castle.domain.castle.event.CastleBuilt;
import nara.castle.domain.castle.event.MetroEnrolled;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.share.actor.akka.NaraProjectionActor;
import nara.share.actor.akka.projection.ReadModelBuilder;
import nara.share.actor.akka.projection.journal.ReadJournalSource;
import nara.share.actor.akka.projection.resume.ResumableProjection;

import java.util.Map;

public class CastleProjectionActor extends NaraProjectionActor {
    //
    private CastleRMStoreLycler storeLycler;

    static public Props props(
            CastleRMStoreLycler storeLycler,
            ReadJournalSource readJournalSource,
            ResumableProjection resumableProjection
    ) {
        //
        return Props.create(CastleProjectionActor.class, () -> new CastleProjectionActor(storeLycler, readJournalSource, resumableProjection));
    }

    public CastleProjectionActor(CastleRMStoreLycler storeLycler, ReadJournalSource readJournalSource, ResumableProjection resumableProjection) {
        //
        super("castle", readJournalSource, resumableProjection);
        this.storeLycler = storeLycler;
    }

    @Override
    protected void configProjection(Map<String, ReadModelBuilder> readModelBuilderMap) {
        //
        readModelBuilderMap.put(CastleBuilt.class.getName(), new CastellanRMBuilder());
        readModelBuilderMap.put(MetroEnrolled.class.getName(), new EnrollmentRMBuilder());
        readModelBuilderMap.put(CastellanModified.class.getName(), new UnitPlateRMBuilder());
    }
}
