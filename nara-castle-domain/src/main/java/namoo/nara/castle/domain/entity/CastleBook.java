package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.spec.event.castlebook.SequenceIncreased;
import namoo.nara.share.domain.Entity;
import namoo.nara.share.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleBook extends Entity {
    //
    Logger logger = LoggerFactory.getLogger(getId());

    private long sequence;

    public CastleBook(String id) {
        //
        super(id);
    }

    public CastleBook() {
        //
        super(CastleBook.class.getSimpleName());    // singleton
        this.sequence = 0L;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CastleBook{");
        sb.append(super.toString());
        sb.append(", sequence=").append(sequence);
        sb.append('}');
        return sb.toString();
    }

    public static CastleBook getSample() {
        //
        CastleBook sample = new CastleBook();
        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static CastleBook fromJson(String json) {
        //
        return JsonUtil.fromJson(json, CastleBook.class);
    }

    public void apply(SequenceIncreased event) {
        //
        sequence = event.getIncreasedSequence();
        logger.debug("Apply result[{}]", toString());
    }

    public long nextSequence() {
        //
        return ++sequence;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }

}