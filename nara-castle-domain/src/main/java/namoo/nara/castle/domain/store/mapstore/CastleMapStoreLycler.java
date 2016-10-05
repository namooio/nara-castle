package namoo.nara.castle.domain.store.mapstore;

import namoo.nara.castle.domain.store.*;

public class CastleMapStoreLycler implements CastleStoreLycler {
    //
    private CastleStore castleStore = new CastleMapStore();
    private AccountBookStore accountBookStore = new AccountBookMapStore();
    private EmailBookStore emailBookStore = new EmailBookMapStore();
    private MetroBookStore metroBookStore = new MetroBookMapStore();

    @Override
    public CastleStore requestCastleStore() {
        //
        return castleStore;
    }

    @Override
    public MetroBookStore requestMetroBookStore() {
        //
        return metroBookStore;
    }

    @Override
    public EmailBookStore requestEmailBookStore() {
        //
        return emailBookStore;
    }

    @Override
    public AccountBookStore requestAccountBookStore() {
        //
        return accountBookStore;
    }
}
