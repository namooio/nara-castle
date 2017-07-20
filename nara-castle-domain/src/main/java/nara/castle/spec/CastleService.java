package nara.castle.spec;

import nara.castle.domain.castle.command.AddEnrollmentCommand;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.ModifyCastellanCommand;

import java.util.concurrent.CompletionStage;

public interface CastleService {
    //
    CompletionStage buildCastle(BuildCastleCommand buildCastleCommand);
    CompletionStage modifyCastellan(String castellanId, ModifyCastellanCommand modifyCastellanCommand);
    CompletionStage addEnrollment(String castellanId, AddEnrollmentCommand addEnrollmentCommand);

    CompletionStage findCastle(String castleId);

    CompletionStage findCastles();
    CompletionStage findCastellans();
}
