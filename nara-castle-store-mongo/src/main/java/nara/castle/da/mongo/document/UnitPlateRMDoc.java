package nara.castle.da.mongo.document;

import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import org.mongodb.morphia.annotations.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Entity("CA_UNIT_PLATE")
@Indexes(
        @Index(
                value = "idx_key_attr_value",
                fields = {
                        @Field("keyAttr"),
                        @Field("keyValue")
                },
                unique = false
        )
)
public class UnitPlateRMDoc {
    //
    @Id
    private String id;

    private String keyValue;
    private KeyAttr keyAttr;

    @Indexed
    private String castellanId;

    public UnitPlateRMDoc() {
        //
    }

    public static UnitPlateRMDoc toDocument(UnitPlateRM unitPlateRM) {
        //
        UnitPlateRMDoc unitPlateRMDoc = new UnitPlateRMDoc();
        BeanUtils.copyProperties(unitPlateRM, unitPlateRMDoc);
        return unitPlateRMDoc;
    }

    public static List<UnitPlateRMDoc> toDocument(List<UnitPlateRM> unitPlateRMs) {
        //
        return unitPlateRMs.stream().map(unitPlateRM -> toDocument(unitPlateRM)).collect(Collectors.toList());
    }

    public UnitPlateRM toModel() {
        //
        UnitPlateRM unitPlateRM = new UnitPlateRM(id);
        BeanUtils.copyProperties(this, unitPlateRM);
        return unitPlateRM;
    }

    public static List<UnitPlateRM> toModel(List<UnitPlateRMDoc> unitPlateRMDocs) {
        //
        return unitPlateRMDocs.stream().map(unitplateRMDoc -> unitplateRMDoc.toModel()).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public KeyAttr getKeyAttr() {
        return keyAttr;
    }

    public void setKeyAttr(KeyAttr keyAttr) {
        this.keyAttr = keyAttr;
    }

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
    }

}
