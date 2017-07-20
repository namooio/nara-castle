package nara.castle.actor.akka.projection.builder;

import nara.castle.domain.castle.event.MetroEnrolled;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.share.actor.akka.projection.ReadModelBuilder;

public class EnrollmentRMBuilder implements ReadModelBuilder<MetroEnrolled> {
    //
    private CastleRMStoreLycler rmStoreLycler;

    public EnrollmentRMBuilder(CastleRMStoreLycler rmStoreLycler) {
        //
        this.rmStoreLycler = rmStoreLycler;
    }

    @Override
    public void build(MetroEnrolled event) {

    }
}
