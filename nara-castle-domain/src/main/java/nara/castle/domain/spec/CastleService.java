package nara.castle.domain.spec;

import nara.castle.domain.spec.command.castle.BuildCastleCommand;
import nara.castle.domain.spec.command.castle.EnrollMetroCommand;

import java.util.concurrent.CompletionStage;

public interface CastleService {
    //
    CompletionStage buildCastle(BuildCastleCommand command);
    CompletionStage enrollMetro(String castleId, EnrollMetroCommand command);

    CompletionStage findCastle(String castleId);

    CompletionStage findCastles();
    CompletionStage findCastellans();
}
