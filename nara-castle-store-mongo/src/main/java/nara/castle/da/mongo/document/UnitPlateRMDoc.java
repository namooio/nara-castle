package nara.castle.da.mongo.document;

import nara.castle.domain.castlequery.model.UnitPlateRM;
import org.mongodb.morphia.annotations.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity("CA_UNIT_PLATE")
@Indexes(
    {
        @Index(value = "idx_unit_plate_key_attr_value",
            fields = {
                @Field("unitPlateRM.keyAttr"),
                @Field("unitPlateRM.keyValue")
            }
        ),
        @Index(value = "idx_unit_plate_castellanId", fields = @Field("unitPlateRM.castellanId"))
    }

)
public class UnitPlateRMDoc {
    //
    @Id
    private String id;
    private UnitPlateRM unitPlateRM;

    public UnitPlateRMDoc() {
        //
    }

    public UnitPlateRMDoc(UnitPlateRM unitPlateRM) {
        //
        this.id = unitPlateRM.getId();
        this.unitPlateRM = unitPlateRM;
    }

    public static List<UnitPlateRMDoc> toDocument(List<UnitPlateRM> unitPlateRMs) {
        //
        return unitPlateRMs.stream().map(unitPlateRM -> new UnitPlateRMDoc(unitPlateRM)).collect(Collectors.toList());
    }

    public static List<UnitPlateRM> toModel(List<UnitPlateRMDoc> unitPlateRMDocs) {
        //
        return unitPlateRMDocs.stream().map(unitplateRMDoc -> unitplateRMDoc.getUnitPlateRM()).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UnitPlateRM getUnitPlateRM() {
        return unitPlateRM;
    }

    public void setUnitPlateRM(UnitPlateRM unitPlateRM) {
        this.unitPlateRM = unitPlateRM;
    }
}
