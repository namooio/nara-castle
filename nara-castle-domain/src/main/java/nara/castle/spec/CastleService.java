package nara.castle.spec;

import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.EnrollmentCommand;

import java.util.concurrent.CompletionStage;

public interface CastleService {
    //
    CompletionStage buildCastle(BuildCastleCommand command);
    CompletionStage enrollMetro(String castleId, EnrollmentCommand command);

    CompletionStage findCastle(String castleId);

    CompletionStage findCastles();
    CompletionStage findCastellans();
}
