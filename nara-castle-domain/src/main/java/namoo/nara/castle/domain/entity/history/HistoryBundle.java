package namoo.nara.castle.domain.entity.history;

public class HistoryBundle {
    //
    private MetroBook metroBook;
    private AccountBook accountBook;

    public HistoryBundle() {
        //
    }

    public MetroBook getMetroBook() {
        return metroBook;
    }

    public void setMetroBook(MetroBook metroBook) {
        this.metroBook = metroBook;
    }

    public AccountBook getAccountBook() {
        return accountBook;
    }

    public void setAccountBook(AccountBook accountBook) {
        this.accountBook = accountBook;
    }
}