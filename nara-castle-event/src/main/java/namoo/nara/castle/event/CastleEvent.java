package namoo.nara.castle.event;

import namoo.nara.share.event.LycleEvent;
import namoo.nara.share.event.LycleType;
import namoo.nara.share.event.rbmq.RabbitEvent;

public class CastleEvent extends LycleEvent implements RabbitEvent {
    //
    public static final String TOPIC_EXCHANGE = CastleEvent.class.getTypeName();

    private String castleId;

    public CastleEvent(LycleType lycleType, String castleId) {
        //
        super(lycleType);
        this.castleId = castleId;
    }

    @Override
    public String getTopicExchange() {
        return TOPIC_EXCHANGE;
    }

    @Override
    public String getRoutingKey() {
        return null;
    }

    public String getCastleId() {
        return castleId;
    }

    public void setCastleId(String castleId) {
        this.castleId = castleId;
    }
}
