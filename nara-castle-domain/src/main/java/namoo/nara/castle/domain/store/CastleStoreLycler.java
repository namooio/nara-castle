package namoo.nara.castle.domain.store;

public interface CastleStoreLycler {
    //
    CastleStore requestCastleStore();
    MetroBookStore requestMetroBookStore();
    EmailBookStore requestEmailBookStore();
    AccountBookStore requestAccountBookStore();
}