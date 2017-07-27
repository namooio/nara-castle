package nara.castle.da.mongo.document;

import nara.castle.domain.castlequery.model.EnrollmentRM;
import org.mongodb.morphia.annotations.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity("CA_ENROLLMENT")
@Indexes(
    @Index(value = "idx_enrollment_castellanId", fields = @Field("enrollmentRM.castellanId"))
)
public class EnrollmentRMDoc {
    //
    @Id
    private String id;
    private EnrollmentRM enrollmentRM;

    public EnrollmentRMDoc() {
        //
    }

    public EnrollmentRMDoc(EnrollmentRM enrollmentRM) {
        //
        this.id = enrollmentRM.getId();
        this.enrollmentRM = enrollmentRM;
    }

    public static List<EnrollmentRMDoc> toDocument(List<EnrollmentRM> enrollmentRMS) {
        //
        return enrollmentRMS.stream().map(enrollmentRM -> new EnrollmentRMDoc(enrollmentRM)).collect(Collectors.toList());
    }

    public static List<EnrollmentRM> toModel(List<EnrollmentRMDoc> enrollmentRMDocs) {
        //
        return enrollmentRMDocs.stream().map(doc -> doc.getEnrollmentRM()).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EnrollmentRM getEnrollmentRM() {
        return enrollmentRM;
    }

    public void setEnrollmentRM(EnrollmentRM enrollmentRM) {
        this.enrollmentRM = enrollmentRM;
    }
}
