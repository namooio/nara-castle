package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.da.mongo.document.history.AccountBookDoc;
import namoo.nara.castle.da.mongo.document.history.CastleStateBookDoc;
import namoo.nara.castle.da.mongo.document.history.MetroBookDoc;
import namoo.nara.castle.domain.entity.history.CastleStateBook;
import namoo.nara.castle.domain.entity.history.HistoryBundle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Document(collection = "HistoryBundle")
public class HistoryBundleDoc {
    //
    @Id
    private String id;

    private CastleStateBookDoc castleStateBook;
    private MetroBookDoc metroBook;
    private AccountBookDoc accountBook;

    public HistoryBundleDoc() {
        //
    }

    public static HistoryBundleDoc newInstance(HistoryBundle history) {
        //
        HistoryBundleDoc historyDoc = new HistoryBundleDoc();
        historyDoc.setId(history.getId());

        CastleStateBook castleStateBook = history.getCastleStateBook();
        MetroBook metroBook = history.getMetroBook();
        AccountBook accountBook = history.getAccountBook();

        if (castleStateBook != null) historyDoc.setCastleStateBook(CastleStateBookDoc.newInstance(castleStateBook));
        if (metroBook != null) historyDoc.setMetroBook(MetroBookDoc.newInstance(metroBook));
        if (accountBook != null) historyDoc.setAccountBook(AccountBookDoc.newInstance(accountBook));

        return historyDoc;
    }

    public HistoryBundle toDomain() {
        //
        HistoryBundle historyBundle = HistoryBundle.newInstance(id);

        if (castleStateBook != null) historyBundle.attachCastleStateBook(castleStateBook.toDomain());
        if (metroBook != null) historyBundle.attachMetroBook(metroBook.toDomain());
        if (accountBook != null) historyBundle.attachAccountBook(accountBook.toDomain());

        return historyBundle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CastleStateBookDoc getCastleStateBook() {
        return castleStateBook;
    }

    public void setCastleStateBook(CastleStateBookDoc castleStateBook) {
        this.castleStateBook = castleStateBook;
    }

    public MetroBookDoc getMetroBook() {
        return metroBook;
    }

    public void setMetroBook(MetroBookDoc metroBook) {
        this.metroBook = metroBook;
    }

    public AccountBookDoc getAccountBook() {
        return accountBook;
    }

    public void setAccountBook(AccountBookDoc accountBook) {
        this.accountBook = accountBook;
    }
}
