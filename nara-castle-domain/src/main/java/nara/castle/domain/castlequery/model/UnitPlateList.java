package nara.castle.domain.castlequery.model;

import nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class UnitPlateList {
    //
    private List<UnitPlateRM> unitPlates;

    public UnitPlateList() {
        //
        this.unitPlates = new ArrayList<>();
    }

    public UnitPlateList(List<UnitPlateRM> plates) {
        //
        this.unitPlates = plates;
    }

    public UnitPlateList(UnitPlateRM unitPlate) {
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
        sample.add(UnitPlateRM.getSample());

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

    public void add(UnitPlateRM unitPlate) {
        // 
        this.unitPlates.add(unitPlate);
    }

    public void remove(UnitPlateRM unitPlate) {
        //
        if (unitPlate != null) {
            unitPlates.remove(unitPlate);
        }
    }

    public void addAll(List<UnitPlateRM> plates) {
        // 
        this.unitPlates.addAll(plates);
    }

    public List<UnitPlateRM> getUnitPlates() {
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