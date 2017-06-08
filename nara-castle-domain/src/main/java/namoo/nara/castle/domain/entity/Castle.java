package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.granule.Email;

public class Castle extends Entity implements Aggregate {
    //
    /**
     * Nation where castle is built
     */
    private String nationId;
    private Castellan castellan;
    private Long builtTime;

    public Castle() {
        //
    }

    public Castle(String id) {
        //
        super(id);
    }

    public Castle(String castleId, String nationId, String metroId, String civilianId, String email) {
        //
        super(castleId);
        this.nationId = nationId;
        this.castellan = new Castellan(new Email(email), new JoinedMetro(nationId, metroId, civilianId));
        this.builtTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("id:'").append(getId()).append('\'');
        sb.append(", castellan:").append(castellan);
        sb.append(", nationId:'").append(nationId).append('\'');
        sb.append(", builtTime:").append(builtTime);
        sb.append('}');
        return sb.toString();
    }

    public static Castle getSample() {
        //
        String nationId = "P";
        String metroId = "P0P";
        String civilianId = "5YC1R@P0P";

        String castleId = CastleContext.getCastleIdBuilder().makeCastleId(nationId, 0);
        Castle castle = new Castle(castleId, nationId, metroId, civilianId, "kchuh@nextree.co.kr");

        return castle;
    }

    public void setValues(NameValueList nameValues) {
        //
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

    public static void main(String[] args) {
        System.out.println(Castle.getSample());
    }

}