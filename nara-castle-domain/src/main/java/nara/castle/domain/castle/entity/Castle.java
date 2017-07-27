package nara.castle.domain.castle.entity;

import nara.share.domain.ValueObject;
import nara.share.util.json.JsonUtil;

public class Castle implements ValueObject {
    //
    private Long builtTime;
    transient Castellan castellan;

    public Castle(Castellan castellan) {
        //
        this.builtTime = System.currentTimeMillis();
        this.castellan = castellan;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Castle{");
        sb.append(", builtTime=").append(builtTime);
        sb.append('}');
        return sb.toString();
    }

    public static Castle getSample() {
        //
        Castle sample = new Castle(Castellan.getSample());
        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static Castle fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Castle.class);
    }

    public Long getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(Long builtTime) {
        this.builtTime = builtTime;
    }

    public static void main(String[] args) {
        //
        System.out.println(Castle.getSample());

    }
}