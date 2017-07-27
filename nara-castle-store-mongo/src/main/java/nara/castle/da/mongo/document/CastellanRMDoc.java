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

    private CastellanRM castellanRM;

    public CastellanRMDoc() {
        //
    }

    public CastellanRMDoc(CastellanRM castellanRM) {
        //
        this.id = castellanRM.getId();
        this.castellanRM = castellanRM;
    }

    public static List<CastellanRM> toModel(List<CastellanRMDoc> castellanRMDocs) {
        //
        return castellanRMDocs.stream()
                .map(castellanRMDoc -> castellanRMDoc.getCastellanRM())
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CastellanRM getCastellanRM() {
        return castellanRM;
    }

    public void setCastellanRM(CastellanRM castellanRM) {
        this.castellanRM = castellanRM;
    }
}
