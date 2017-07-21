package nara.castle.spec;

import nara.castle.domain.castle.command.AddEnrollmentCommand;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.ModifyCastellanCommand;
import nara.castle.domain.castle.command.WithdrawMetroCommand;
import nara.castle.domain.castlequery.query.EnrolledCheckQuery;
import nara.castle.domain.castlequery.query.FindUnitPlatesQuery;

import java.util.concurrent.CompletionStage;

public interface CastleService {
    //
    CompletionStage buildCastle(BuildCastleCommand buildCastleCommand);
    CompletionStage modifyCastellan(String castellanId, ModifyCastellanCommand modifyCastellanCommand);
    CompletionStage addEnrollment(String castellanId, AddEnrollmentCommand addEnrollmentCommand);
    CompletionStage withdrawMetro(String castellanId, WithdrawMetroCommand withdrawMetroCommand);

    CompletionStage findCastellan(String castellanId);
    CompletionStage findCastellans();

    CompletionStage findEnrollments(String castellanId);
    CompletionStage findUnitPlate(FindUnitPlatesQuery findUnitPlatesQuery);

    CompletionStage checkEnrolled(EnrolledCheckQuery enrolledCheckQuery);
}
