package namoo.nara.castle.da.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CA_CASTLE_SEQ")
public class CastleSequenceDoc {

    public static final String NARA_ID = "NARA";

    @Id
    private String naraId;
    private long castleSequence;

    public CastleSequenceDoc() {
        //
    }

    public String getNaraId() {
        return naraId;
    }

    public void setNaraId(String naraId) {
        this.naraId = naraId;
    }

    public long getCastleSequence() {
        return castleSequence;
    }

    public void setCastleSequence(long castleSequence) {
        this.castleSequence = castleSequence;
    }
}
