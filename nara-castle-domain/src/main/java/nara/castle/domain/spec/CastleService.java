package nara.castle.domain.spec;

import nara.castle.domain.entity.Castle;
import nara.castle.domain.spec.command.castle.BuildCastleCommand;
import nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import nara.castle.domain.view.CastellanView;
import nara.castle.domain.view.CastleView;

import java.util.List;

public interface CastleService {
    //
    String buildCastle(BuildCastleCommand command);
    void enrollMetro(String castleId, EnrollMetroCommand command);

    Castle findCastle(String castleId);

    List<CastleView> findCastles();
    List<CastellanView> findCastellans();
}
