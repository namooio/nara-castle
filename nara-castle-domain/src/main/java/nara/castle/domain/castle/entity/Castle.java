package nara.castle.domain.castle.entity;

import nara.share.domain.ValueObject;
import nara.share.util.json.JsonUtil;

public class Castle implements ValueObject {
    //
    private Long builtTime;
    transient Castellan castellan;

    public Castle() {
        //
        this.builtTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Castle{");
        sb.append(super.toString());
        sb.append(", builtTime=").append(builtTime);
        sb.append('}');
        return sb.toString();
    }

    public static Castle getSample() {
        //
        Castle sample = new Castle();

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

//    public boolean isEnrolledMetro(String metroId, String civilianId) {
//        //
//        return enrollments
//                .stream()
//                .filter(enrollment -> metroId.equals(enrollment.getMetroId()) && civilianId.equals(enrollment.getCivilianId()))
//                .findFirst()
//                .isPresent();
//    }
//
//    @Override
//    public void apply(NaraEvent event) {
//        //
//        if (event instanceof CastleBuilt) {
//            CastleBuilt castleBuilt = (CastleBuilt) event;
//            Castle castle = castleBuilt.getCastle();
//            zone = castle.getZone();
//            enrollments = castle.getEnrollments();
//        }
//        else if (event instanceof MetroEnrolled) {
//            MetroEnrolled metroEnrolled = (MetroEnrolled) event;
//            enrollments.add(metroEnrolled.getEnrollment());
//        }
//        else if (event instanceof MetroWithdrawn) {
//            MetroWithdrawn metroWithdrawn = (MetroWithdrawn) event;
//            String metroId = metroWithdrawn.getMetroId();
//            String civilianId = metroWithdrawn.getCivilianId();
//            Enrollment targetEnrollment = enrollments
//                    .stream()
//                    .filter(enrollment -> enrollment.getMetroId().equals(metroId) && enrollment.getCivilianId().equals(civilianId))
//                    .findFirst()
//                    .orElse(null);
//
//            if (targetEnrollment == null) throw new NaraException(String.format("Metro enrollment for %s not found.", event));
//            targetEnrollment.withdraw();
//        }
//    }

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