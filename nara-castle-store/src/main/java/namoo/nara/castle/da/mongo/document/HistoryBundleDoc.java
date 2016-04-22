package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.da.mongo.document.history.AccountBookDoc;
import namoo.nara.castle.da.mongo.document.history.CastleStateBookDoc;
import namoo.nara.castle.da.mongo.document.history.MetroBookDoc;
import namoo.nara.castle.domain.entity.history.AccountBook;
import namoo.nara.castle.domain.entity.history.CastleStateBook;
import namoo.nara.castle.domain.entity.history.HistoryBundle;
import namoo.nara.castle.domain.entity.history.MetroBook;
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

    private CastleStateBookDoc castleStateBookDoc;
    private MetroBookDoc metroBookDoc;
    private AccountBookDoc accountBookDoc;

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

        if (castleStateBook != null) historyDoc.setCastleStateBookDoc(CastleStateBookDoc.newInstance(castleStateBook));
        if (metroBook != null) historyDoc.setMetroBookDoc(MetroBookDoc.newInstance(metroBook));
        if (accountBook != null) historyDoc.setAccountBookDoc(AccountBookDoc.newInstance(accountBook));

        return historyDoc;
    }

    public HistoryBundle toDomain() {
        //
        HistoryBundle historyBundle = HistoryBundle.newInstance(id);

        if (castleStateBookDoc != null) historyBundle.attachCastleStateBook(castleStateBookDoc.toDomain());
        if (metroBookDoc != null) historyBundle.attachMetroBook(metroBookDoc.toDomain());
        if (accountBookDoc != null) historyBundle.attachAccountBook(accountBookDoc.toDomain());

        return historyBundle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CastleStateBookDoc getCastleStateBookDoc() {
        return castleStateBookDoc;
    }

    public void setCastleStateBookDoc(CastleStateBookDoc castleStateBookDoc) {
        this.castleStateBookDoc = castleStateBookDoc;
    }

    public MetroBookDoc getMetroBookDoc() {
        return metroBookDoc;
    }

    public void setMetroBookDoc(MetroBookDoc metroBookDoc) {
        this.metroBookDoc = metroBookDoc;
    }

    public AccountBookDoc getAccountBookDoc() {
        return accountBookDoc;
    }

    public void setAccountBookDoc(AccountBookDoc accountBookDoc) {
        this.accountBookDoc = accountBookDoc;
    }
}
