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

    protected HistoryBundle(String castleId) {
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

    public void setCastleStateBook(CastleStateBook castleStateBook) {
        this.castleStateBook = castleStateBook;
    }

    public MetroBook getMetroBook() {
        //
        if (metroBook == null) {
            this.metroBook = new MetroBook();
        }

        return metroBook;
    }

    public void setMetroBook(MetroBook metroBook) {
        this.metroBook = metroBook;
    }

    public AccountBook getAccountBook() {
        //
        if (accountBook == null) {
            this.accountBook = new AccountBook();
        }

        return accountBook;
    }

    public void setAccountBook(AccountBook accountBook) {
        this.accountBook = accountBook;
    }
}