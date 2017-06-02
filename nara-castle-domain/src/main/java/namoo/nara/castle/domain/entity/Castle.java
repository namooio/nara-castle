package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.granule.Email;

public class Castle extends Entity implements Aggregate {

    private Castellan castellan;

    private String nationId;
    private String originMetroId;
    private String originCivilianId;

    private Long builtTime;

    public Castle() {

    }

    public Castle(String id) {
        super(id);
    }

    public Castle(String castleId, String nationId, String originMetroId, String originCivilianId) {

        super(castleId);
        this.nationId = nationId;
        this.originMetroId = originMetroId;
        this.originCivilianId = originCivilianId;
        this.builtTime = System.currentTimeMillis();
        this.castellan = new Castellan();
        this.castellan.addJoinedMetro(originMetroId, originCivilianId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("id:'").append(getId()).append('\'');
        sb.append(", castellan:").append(castellan);
        sb.append(", nationId:'").append(nationId).append('\'');
        sb.append(", originMetroId:'").append(originMetroId).append('\'');
        sb.append(", originCivilianId:'").append(originCivilianId).append('\'');
        sb.append(", builtTime:").append(builtTime);
        sb.append('}');
        return sb.toString();
    }

    public static Castle getSample() {

        String nationId = "P";
        String originMetroId = "P0P";
        String originCivilianId = "5YC1R@P0P";

        String castleId = CastleContext.getCastleIdBuilder().makeCastleId(nationId, 0);
        Castle castle = new Castle(castleId, nationId, originMetroId, originCivilianId);

        Castellan castellan = castle.getCastellan();
        castellan.getEmails().add(new Email("kchuh@nextree.co.kr"));
        castellan.addJoinedMetro("P0Q", "2@P0Q");

        NameValueList nameValues = new NameValueList();
        nameValues.add("castellan", castellan.toJson());

        castle.setValues(nameValues);
        return castle;
    }

    public void setValues(NameValueList nameValues) {

        nameValues.getList().forEach(nameValue -> {
            if ("castellan".equals(nameValue.getName())) this.setCastellan(Castellan.fromJson(nameValue.getValue()));
        });
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
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

    public String getOriginCivilianId() {
        return originCivilianId;
    }

    public void setOriginCivilianId(String originCivilianId) {
        this.originCivilianId = originCivilianId;
    }

    public static void main(String[] args) {
        System.out.println(Castle.getSample());
    }

}