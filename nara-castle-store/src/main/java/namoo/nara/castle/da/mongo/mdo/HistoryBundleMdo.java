package namoo.nara.castle.da.mongo.mdo;

import namoo.nara.castle.da.mongo.mdo.history.AccountBookMdo;
import namoo.nara.castle.da.mongo.mdo.history.CastleStateBookMdo;
import namoo.nara.castle.da.mongo.mdo.history.MetroBookMdo;
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
public class HistoryBundleMdo {
    //
    @Id
    private String id;

    private CastleStateBookMdo castleStateBookMdo;
    private MetroBookMdo metroBookMdo;
    private AccountBookMdo accountBookMdo;

    public HistoryBundleMdo() {
        //
    }

    public static HistoryBundleMdo newInstance(HistoryBundle history) {
        //
        HistoryBundleMdo historyMdo = new HistoryBundleMdo();
        historyMdo.setId(history.getId());

        CastleStateBook castleStateBook = history.getCastleStateBook();
        MetroBook metroBook = history.getMetroBook();
        AccountBook accountBook = history.getAccountBook();

        if (castleStateBook != null) historyMdo.setCastleStateBookMdo(CastleStateBookMdo.newInstance(castleStateBook));
        if (metroBook != null) historyMdo.setMetroBookMdo(MetroBookMdo.newInstance(metroBook));
        if (accountBook != null) historyMdo.setAccountBookMdo(AccountBookMdo.newInstance(accountBook));

        return historyMdo;
    }

    public HistoryBundle getDomain() {
        //
        HistoryBundle historyBundle = HistoryBundle.newInstance(id);

        if (castleStateBookMdo != null) historyBundle.setCastleStateBook(castleStateBookMdo.getDomain());
        if (metroBookMdo != null) historyBundle.setMetroBook(metroBookMdo.getDomain());
        if (accountBookMdo != null) historyBundle.setAccountBook(accountBookMdo.getDomain());

        return historyBundle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CastleStateBookMdo getCastleStateBookMdo() {
        return castleStateBookMdo;
    }

    public void setCastleStateBookMdo(CastleStateBookMdo castleStateBookMdo) {
        this.castleStateBookMdo = castleStateBookMdo;
    }

    public MetroBookMdo getMetroBookMdo() {
        return metroBookMdo;
    }

    public void setMetroBookMdo(MetroBookMdo metroBookMdo) {
        this.metroBookMdo = metroBookMdo;
    }

    public AccountBookMdo getAccountBookMdo() {
        return accountBookMdo;
    }

    public void setAccountBookMdo(AccountBookMdo accountBookMdo) {
        this.accountBookMdo = accountBookMdo;
    }
}
