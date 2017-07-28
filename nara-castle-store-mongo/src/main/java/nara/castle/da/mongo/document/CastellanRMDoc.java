package nara.castle.da.mongo.document;

import nara.castle.domain.castlequery.model.CastellanRM;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;
import java.util.stream.Collectors;

@Entity("CA_CASTELLAN")
public class CastellanRMDoc {
    //
    @Id
    private String id;

    private CastellanRM rm;

    public CastellanRMDoc() {
        //
    }

    public CastellanRMDoc(CastellanRM rm) {
        //
        this.id = rm.getId();
        this.rm = rm;
    }

    public static List<CastellanRM> toModel(List<CastellanRMDoc> docs) {
        //
        return docs.stream().map(doc -> doc.getRm()).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CastellanRM getRm() {
        return rm;
    }

    public void setRm(CastellanRM rm) {
        this.rm = rm;
    }
}
