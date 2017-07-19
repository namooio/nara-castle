package nara.castle.domain.castlequery.model;

import nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class UnitPlateList {
    //
    private List<UnitPlate> unitPlates;

    public UnitPlateList() {
        //
        this.unitPlates = new ArrayList<>();
    }

    public UnitPlateList(List<UnitPlate> plates) {
        //
        this.unitPlates = plates;
    }

    public UnitPlateList(UnitPlate unitPlate) {
        //
        this();
        this.unitPlates.add(unitPlate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnitPlateList{");
        sb.append("unitPlates=").append(unitPlates);
        sb.append('}');
        return sb.toString();
    }

    public static UnitPlateList getSample() {
        //
        UnitPlateList sample = new UnitPlateList();
        sample.add(UnitPlate.getSample());

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static UnitPlateList fromJson(String json) {
        //
        return JsonUtil.fromJson(json, UnitPlateList.class);
    }

    public void add(UnitPlate unitPlate) {
        // 
        this.unitPlates.add(unitPlate);
    }

    public void remove(UnitPlate unitPlate) {
        //
        if (unitPlate != null) {
            unitPlates.remove(unitPlate);
        }
    }

    public void addAll(List<UnitPlate> plates) {
        // 
        this.unitPlates.addAll(plates);
    }

    public List<UnitPlate> getUnitPlates() {
        // 
        return unitPlates;
    }

    public int size() {
        return unitPlates.size();
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().toJson());
    }
}