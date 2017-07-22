package nara.castle.domain.castlequery.model;

import nara.castle.domain.castle.entity.Castellan;
import nara.share.domain.Entity;
import nara.share.util.json.JsonUtil;

public class CastellanJsonRM extends Entity {
    //
    private String json;

    public CastellanJsonRM() {
        //
    }

    public CastellanJsonRM(String id) {
        //
        super(id);
    }

    public CastellanJsonRM(Castellan castellan) {
        //
        super(castellan.getId());
        this.json = castellan.toJson();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CastellanJsonRM{");
        sb.append("json='").append(json).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static CastellanJsonRM getSample() {
        //
        Castellan castellan = Castellan.getSample();
        CastellanJsonRM sample = new CastellanJsonRM(castellan);

        return sample;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static CastellanJsonRM fromJson(String json) {
        return JsonUtil.fromJson(json, CastellanJsonRM.class);
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}