package namoo.nara.castle.domain.spec;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;

import java.util.List;

public interface CastleService {
    //
    String enrollMetro(EnrollMetroCommand command);
    List<Castle> findCastles();
}
