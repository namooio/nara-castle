package nara.castle.cp;

import nara.castle.da.mongo.CastellanRMMongoStore;
import nara.castle.da.mongo.EnrollmentRMMongoStore;
import nara.castle.da.mongo.UnitPlateRMMongoStore;
import nara.castle.domain.castlequery.store.CastellanRMStore;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.castle.domain.castlequery.store.EnrollmentRMStore;
import nara.castle.domain.castlequery.store.UnitPlateRMStore;
import org.mongodb.morphia.Datastore;

public class CastleTestRMMongoStoreLycler implements CastleRMStoreLycler {
    //
    private CastellanRMStore castellanRMStore;
    private EnrollmentRMStore enrollmentRMStore;
    private UnitPlateRMStore unitPlateRMStore;

    public CastleTestRMMongoStoreLycler(Datastore datastore) {
        //
        castellanRMStore = new CastellanRMMongoStore(datastore);
        enrollmentRMStore = new EnrollmentRMMongoStore(datastore);
        unitPlateRMStore = new UnitPlateRMMongoStore(datastore);
    }

    @Override
    public CastellanRMStore requestCastellanRMStore() {
        //
        return castellanRMStore;
    }

    @Override
    public EnrollmentRMStore requestEnrollmentRMStore() {
        //
        return enrollmentRMStore;
    }

    @Override
    public UnitPlateRMStore requestUnitPlateRMStore() {
        //
        return unitPlateRMStore;
    }
}
