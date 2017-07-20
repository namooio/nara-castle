package nara.castle.actor.akka.projection.builder;

import nara.castle.domain.castle.event.CastleBuilt;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.share.actor.akka.projection.ReadModelBuilder;

public class CastellanRMBuilder implements ReadModelBuilder<CastleBuilt> {
    //
    private CastleRMStoreLycler rmStoreLycler;

    public CastellanRMBuilder(CastleRMStoreLycler rmStoreLycler) {
        //
        this.rmStoreLycler = rmStoreLycler;
    }

    @Override
    public void build(CastleBuilt event) {

    }
}
