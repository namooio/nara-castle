package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;

import java.time.ZonedDateTime;
import java.util.Locale;

public class Castle extends Entity implements Aggregate {
    //
    private Castellan castellan;

    private Locale locale; // optional
    private ZonedDateTime builtTime;

    private String originMetroId;

    private Long version;

    public Castle(String id) {
        //
        super(id);
    }

    @Override
    public String toString() {
        return "Castle{" +
                "castellan=" + castellan +
                ", locale=" + locale +
                ", builtTime=" + builtTime +
                ", originMetroId='" + originMetroId + '\'' +
                ", version=" + version +
                '}';
    }

    public static Castle getSample() {
        //
        Castle castle = Castle.newInstance(1, "kchuh@nextree.co.kr", "1", Locale.KOREA);
        return castle;
    }


    public static Castle newInstance(long castleSequence, String originMetroId) {
        //
        CastleIdBuilder castleIdBuilder = CastleContext.getCastleIdBuilder();
        String castleId = castleIdBuilder.makeCastleId(castleSequence);
        Castle castle = new Castle(castleId);
        castle.setCastellan(Castellan.newInstance());
        castle.setBuiltTime(ZonedDateTime.now());
        castle.setOriginMetroId(originMetroId);

        return castle;
    }

    public static Castle newInstance(long castleSequence, String castellanEmail, String originMetroId) {
        //
        Castle castle = newInstance(castleSequence, originMetroId);
        Castellan castellan = castle.getCastellan();
        castellan.addEmail(castellanEmail);
        return castle;
    }

    public static Castle newInstance(long castleSequence, String castellanEmail, String originMetroId, Locale locale) {
        //
        Castle castle = newInstance(castleSequence, castellanEmail, originMetroId);
        castle.setLocale(locale);
        return castle;
    }

    public Castellan getCastellan() {
        return castellan;
    }

    public void setCastellan(Castellan castellan) {
        this.castellan = castellan;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public ZonedDateTime getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(ZonedDateTime builtTime) {
        this.builtTime = builtTime;
    }

    public String getOriginMetroId() {
        return originMetroId;
    }

    public void setOriginMetroId(String originMetroId) {
        this.originMetroId = originMetroId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public static void main(String[] args) {
        //
        System.out.println(Castle.getSample());
    }

}