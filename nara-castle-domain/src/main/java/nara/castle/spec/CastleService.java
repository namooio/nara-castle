package nara.castle.spec;

import nara.castle.domain.castle.command.AddEnrollmentCommand;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.ModifyCastellanCommand;
import nara.castle.domain.castle.command.WithdrawMetroCommand;
import nara.castle.domain.castlequery.model.KeyAttr;

import java.util.concurrent.CompletionStage;

public interface CastleService {
    //
    CompletionStage buildCastle(BuildCastleCommand buildCastleCommand);
    CompletionStage modifyCastellan(String castellanId, ModifyCastellanCommand modifyCastellanCommand);
    CompletionStage addEnrollment(String castellanId, AddEnrollmentCommand addEnrollmentCommand);
    CompletionStage withdrawMetro(String castellanId, WithdrawMetroCommand withdrawMetroCommand);
    CompletionStage demolishCastle(String castellanId);

    CompletionStage findCastellan(String castellanId);
    CompletionStage findCastellans();
    CompletionStage existsCastellan(String castellanId);

    CompletionStage findEnrollments(String castellanId);
    CompletionStage findUnitPlates(KeyAttr keyAttr, String keyValue);

    CompletionStage checkEnrolled(String castellanId, String metroId);
}
