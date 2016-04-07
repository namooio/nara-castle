package namoo.nara.castle.da.mongo.mdo;

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
}
