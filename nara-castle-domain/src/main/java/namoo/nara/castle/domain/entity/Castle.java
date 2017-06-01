package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.util.locale.LocaleUtil;

import java.util.Locale;

public class Castle extends Entity implements Aggregate {

    private Castellan castellan;

    private String originMetroId;
    private String originCitizenId;
    private Long builtTime;

    private Locale locale;

    public Castle() {

    }

    public Castle(String id, String originMetroId, String originCivilianId) {
        super(id);
        this.originMetroId = originMetroId;
        this.originCitizenId = originCivilianId;
        this.builtTime = System.currentTimeMillis();
        this.castellan = new Castellan();
        this.castellan.addJoinedMetro(originMetroId, originCivilianId);
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

        String originMetroId = "P0P";
        String originCivilianId = "5YC1R@P0P";
        Castellan castellan = new Castellan();
        castellan.addEmail("kchuh@nextree.co.kr");

        Castle castle = new Castle("1", originMetroId, originCivilianId);
        // TODO
        castle.setLocale(Locale.KOREA);
        return castle;
    }

    public void setValues(NameValueList nameValues) {

        nameValues.getList().forEach(nameValue -> {
            if ("castellan".equals(nameValue.getName())) this.setCastellan(Castellan.fromJson(nameValue.getValue()));
            else if ("locale".equals(nameValue.getName())) this.setLocale(LocaleUtil.toLocale(nameValue.getValue()));
        });
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
        System.out.println(Castle.getSample());
    }

}