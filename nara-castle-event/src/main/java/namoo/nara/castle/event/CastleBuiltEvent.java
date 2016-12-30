package namoo.nara.castle.event;

import namoo.nara.share.event.rbmq.RabbitEvent;

public class CastleBuiltEvent implements RabbitEvent {
    //
    public static final String TOPIC_EXCHANGE = CastleBuiltEvent.class.getTypeName();

    private String castleId;
    private String castellanEmail;

    public CastleBuiltEvent() {
        //
    }

    public CastleBuiltEvent(String castleId, String castellanEmail) {
        //
        this.castleId = castleId;
        this.castellanEmail = castellanEmail;
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

    public String getCastellanEmail() {
        return castellanEmail;
    }

    public void setCastellanEmail(String castellanEmail) {
        this.castellanEmail = castellanEmail;
    }
}
