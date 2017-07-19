package nara.castle.domain.castlebook.entity;

import nara.castle.domain.castlebook.event.SequenceIncreased;
import nara.share.domain.Aggregate;
import nara.share.domain.Entity;
import nara.share.domain.event.NaraEvent;
import nara.share.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleBook extends Entity implements Aggregate {
    //
    Logger logger = LoggerFactory.getLogger(getId());

    private long sequence;

    public CastleBook() {
        //
    }

    public CastleBook(String id) {
        //
        super(id);
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

    @Override
    public void apply(NaraEvent event) {
        //
        if (event instanceof SequenceIncreased) {
            SequenceIncreased sequenceIncreased = (SequenceIncreased) event;
            sequence = sequenceIncreased.getCastleBook().getSequence();
        }
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