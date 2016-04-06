package namoo.nara.castle.domain.store;

public interface CastleStoreLycler {
    //
    CastleStore requestCastleStore();
    CastellanStore requestCastellanStore();
    ContactBundleStore requestContactBundleStore();
    HistoryBundleStore requestHistoryBundleStore();
}