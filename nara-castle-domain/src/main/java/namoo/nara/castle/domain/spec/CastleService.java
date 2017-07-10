package namoo.nara.castle.domain.spec;

import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.view.CastellanView;
import namoo.nara.castle.domain.view.CastleView;

import java.util.List;

public interface CastleService {
    //
    String buildCastle(BuildCastleCommand command);
    void enrollMetro(String castleId, EnrollMetroCommand command);

    List<CastleView> findCastles();
    List<CastellanView> findCastellans();
}
