package namoo.nara.castle.domain.entity.history;

import namoo.nara.share.domain.util.Identifiable;

public class HistoryBundle implements Identifiable{
    //
    private String castleId;
    private CastleStateBook castleStateBook;
    private MetroBook metroBook;
    private AccountBook accountBook;

    public HistoryBundle() {
        //
    }

    private HistoryBundle(String castleId) {
        //
        this.castleId = castleId;
    }

    public static HistoryBundle newInstance(String castleId) {
        //
        return new HistoryBundle(castleId);
    }

    @Override
    public String getId() {
        return castleId;
    }

    public CastleStateBook getCastleStateBook() {
        //
        if (castleStateBook == null) {
            this.castleStateBook = new CastleStateBook();
        }

        return castleStateBook;
    }

    public void attachCastleStateBook(CastleStateBook castleStateBook) {
        this.castleStateBook = castleStateBook;
    }

    public void detatchCastleStateBook() {
        //
        castleStateBook.clear();
    }

    public MetroBook getMetroBook() {
        //
        if (metroBook == null) {
            this.metroBook = new MetroBook();
        }

        return metroBook;
    }

    public void attachMetroBook(MetroBook metroBook) {
        this.metroBook = metroBook;
    }

    public void detatchMetroBook() {
        this.metroBook.clear();
    }

    public AccountBook getAccountBook() {
        //
        if (accountBook == null) {
            this.accountBook = new AccountBook();
        }

        return accountBook;
    }

    public void attachAccountBook(AccountBook accountBook) {
        this.accountBook = accountBook;
    }

    public void detatchAccountBook() {
        //
        this.accountBook.clear();
    }
}