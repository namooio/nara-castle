//package nara.castle.cp;
//
//import nara.castle.da.mongo.CastellanViewMongoStore;
//import nara.castle.da.mongo.CastleViewMongoStore;
//import nara.castle.domain.castlequery.store.CastellanRMStore;
//import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
//import org.mongodb.morphia.Datastore;
//
//public class CastleViewMongoStoreLycler implements CastleRMStoreLycler {
//    //
//    private CastleViewStore castleViewStore;
//    private CastellanRMStore castellanViewStore;
//
//    public CastleViewMongoStoreLycler(Datastore datastore) {
//        //
//        this.castleViewStore = new CastleViewMongoStore(datastore);
//        this.castellanViewStore = new CastellanViewMongoStore(datastore);
//    }
//
//    @Override
//    public CastleViewStore requestCastleViewStore() {
//        //
//        return castleViewStore;
//    }
//
//    @Override
//    public CastellanRMStore requestCastellanViewStore() {
//        //
//        return castellanViewStore;
//    }
//}
