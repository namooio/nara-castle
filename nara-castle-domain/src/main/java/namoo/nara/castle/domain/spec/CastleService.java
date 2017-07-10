package namoo.nara.castle.domain.spec;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;

import java.util.List;

public interface CastleService {
    //
    String buildCastle(BuildCastleCommand command);
    void enrollMetro(String castleId, EnrollMetroCommand command);
    List<Castle> findCastles();
}
