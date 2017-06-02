package namoo.nara.castle.da.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CA_CASTLE_SEQ")
public class CastleSequenceDoc {

    @Id
    private String nationId;
    private long castleSequence;

    public CastleSequenceDoc() {

    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
    }

    public long getCastleSequence() {
        return castleSequence;
    }

    public void setCastleSequence(long castleSequence) {
        this.castleSequence = castleSequence;
    }
}
