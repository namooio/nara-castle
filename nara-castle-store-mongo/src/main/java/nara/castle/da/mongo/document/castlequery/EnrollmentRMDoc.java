package nara.castle.da.mongo.document.castlequery;

import nara.castle.domain.castlequery.model.EnrollmentRM;
import org.mongodb.morphia.annotations.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity("CA_ENROLLMENT")
@Indexes(
    @Index(fields = @Field("rm.castellanId"))
)
public class EnrollmentRMDoc {
    //
    @Id
    private String id;
    private EnrollmentRM rm;

    public EnrollmentRMDoc() {
        //
    }

    public EnrollmentRMDoc(EnrollmentRM rm) {
        //
        this.id = rm.getId();
        this.rm = rm;
    }

    public static List<EnrollmentRMDoc> toDocument(List<EnrollmentRM> rms) {
        //
        return rms.stream().map(rm -> new EnrollmentRMDoc(rm)).collect(Collectors.toList());
    }

    public static List<EnrollmentRM> toModel(List<EnrollmentRMDoc> docs) {
        //
        return docs.stream().map(doc -> doc.getRm()).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EnrollmentRM getRm() {
        return rm;
    }

    public void setRm(EnrollmentRM rm) {
        this.rm = rm;
    }
}
