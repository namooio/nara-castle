package namoo.nara.castle.domain.spec.event.castlebook;

import namoo.nara.share.domain.event.NaraEvent;

public class SequenceIncreased implements NaraEvent {
    //
    private long increasedSequence;

    public SequenceIncreased(long increasedSequence) {
        //
        this.increasedSequence = increasedSequence;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("increasedSequence:").append(increasedSequence);
        sb.append('}');
        return sb.toString();
    }

    public long getIncreasedSequence() {
        return increasedSequence;
    }
}
