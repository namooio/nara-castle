package namoo.nara.castle.da.mapstore;

import namoo.nara.castle.domain.store.*;

public class CastleMapStoreLycler implements CastleStoreLycler {
    //
    private CastleStore castleStore;
    private CastellanStore castellanStore;
    private EnrollmentStore enrollmentStore;
    private UnitPlateStore unitPlateStore;

    public CastleMapStoreLycler() {
        //
        castleStore = new CastleMapStore();
        castellanStore = new CastellanMapStore();
        enrollmentStore = new EnrollmentMapStore();
        unitPlateStore = new UnitPlateMapStore();
    }

    @Override
    public CastleStore requestCastleStore() {
        //
        return castleStore;
    }

    @Override
    public CastellanStore requestCastellanStore() {
        //
        return castellanStore;
    }

    @Override
    public EnrollmentStore requestEnrollmentStore() {
        //
        return enrollmentStore;
    }

    @Override
    public UnitPlateStore requestUnitPlateStore() {
        //
        return unitPlateStore;
    }
}
