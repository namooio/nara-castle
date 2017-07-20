package nara.castle.actor.akka.projection.builder;

import nara.castle.domain.castle.event.CastellanModified;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.share.actor.akka.projection.ReadModelBuilder;

public class UnitPlateRMBuilder implements ReadModelBuilder<CastellanModified> {
    //
    private CastleRMStoreLycler rmStoreLycler;

    public UnitPlateRMBuilder(CastleRMStoreLycler rmStoreLycler) {
        //
        this.rmStoreLycler = rmStoreLycler;
    }

    @Override
    public void build(CastellanModified event) {

    }
}
