package nara.castle.da.mongo.document;

import nara.castle.domain.castlequery.model.UnitPlateRM;
import org.mongodb.morphia.annotations.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity("CA_UNIT_PLATE")
@Indexes({
    @Index(
        fields = {
            @Field("rm.keyAttr"),
            @Field("rm.keyValue")
        }
    ),
    @Index(fields = @Field("rm.castellanId"))
})
public class UnitPlateRMDoc {
    //
    @Id
    private String id;
    private UnitPlateRM rm;

    public UnitPlateRMDoc() {
        //
    }

    public UnitPlateRMDoc(UnitPlateRM rm) {
        //
        this.id = rm.getId();
        this.rm = rm;
    }

    public static List<UnitPlateRMDoc> toDocument(List<UnitPlateRM> rms) {
        //
        return rms.stream().map(rm -> new UnitPlateRMDoc(rm)).collect(Collectors.toList());
    }

    public static List<UnitPlateRM> toModel(List<UnitPlateRMDoc> docs) {
        //
        return docs.stream().map(doc -> doc.getRm()).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UnitPlateRM getRm() {
        return rm;
    }

    public void setRm(UnitPlateRM rm) {
        this.rm = rm;
    }
}
