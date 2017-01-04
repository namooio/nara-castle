package namoo.nara.castle.event;

import namoo.nara.share.event.rbmq.RabbitEvent;

public class CastleBuiltEvent implements RabbitEvent {
    //
    public static final String TOPIC_EXCHANGE = CastleBuiltEvent.class.getTypeName();

    private String castleId;
    private String castellanEmail;
    private String originMetroId;
    private String originCitizenId;

    public CastleBuiltEvent(String castleId, String castellanEmail, String originMetroId, String originCitizenId) {
        //
        this.castleId = castleId;
        this.castellanEmail = castellanEmail;
        this.originMetroId = originMetroId;
        this.originCitizenId = originCitizenId;
    }

    @Override
    public String getTopicExchange() {
        return TOPIC_EXCHANGE;
    }

    @Override
    public String getRoutingKey() {
        return originMetroId;
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

    public String getOriginMetroId() {
        return originMetroId;
    }

    public void setOriginMetroId(String originMetroId) {
        this.originMetroId = originMetroId;
    }

    public String getOriginCitizenId() {
        return originCitizenId;
    }

    public void setOriginCitizenId(String originCitizenId) {
        this.originCitizenId = originCitizenId;
    }
}
