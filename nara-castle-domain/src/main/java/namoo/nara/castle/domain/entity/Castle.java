package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;

import java.util.Locale;

public class Castle extends Entity implements Aggregate {
    //
    private Castellan castellan;
    private Long builtTime;
    private String originMetroId;
    private String originCitizenId;

    private Locale locale;

    public Castle() {
        //
    }

    public Castle(String id) {
        super(id);
    }

    public Castle(String id, Castellan castellan, String originMetroId, String originCitizenId) {
        //
        super(id);
        this.castellan = castellan;
        this.builtTime = System.currentTimeMillis();
        this.originMetroId = originMetroId;
        this.originCitizenId = originCitizenId;
    }

    public Castle(String id, Castellan castellan, String originMetroId, String originCitizenId, Long builtTime) {
        //
        super(id);
        this.castellan = castellan;
        this.builtTime = builtTime;
        this.originMetroId = originMetroId;
        this.originCitizenId = originCitizenId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("castellan:").append(castellan);
        sb.append(", locale:").append(locale);
        sb.append(", builtTime:").append(builtTime);
        sb.append(", originMetroId:'").append(originMetroId).append('\'');
        sb.append(", originCitizenId:'").append(originCitizenId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Castle getSample() {
        //
        Castellan castellan = new Castellan();
        castellan.addEmail("kchuh@nextree.co.kr");
        castellan.addJoinedMetro("1", "1@1");
        Castle castle = new Castle("1", castellan, "1", "1@1");
        castle.setLocale(Locale.KOREA);
        return castle;
    }

    public Castellan getCastellan() {
        return castellan;
    }

    public void setCastellan(Castellan castellan) {
        this.castellan = castellan;
    }

    public Long getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(Long builtTime) {
        this.builtTime = builtTime;
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

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public static void main(String[] args) {
        //
        System.out.println(Castle.getSample());
    }

}