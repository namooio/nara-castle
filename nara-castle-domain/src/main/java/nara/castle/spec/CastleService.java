package nara.castle.spec;

import nara.castle.domain.castle.command.*;
import nara.castle.domain.castlequery.query.*;

import java.util.concurrent.CompletionStage;

public interface CastleService {
    //
    CompletionStage buildCastle(BuildCastleCommand buildCastleCommand);
    CompletionStage modifyCastellan(ModifyCastellanCommand modifyCastellanCommand);
    CompletionStage addEnrollment(AddEnrollmentCommand addEnrollmentCommand);
    CompletionStage withdrawMetro(WithdrawMetroCommand withdrawMetroCommand);
    CompletionStage demolishCastle(DemolishCastleCommand demolishCastleCommand);

    CompletionStage findCastellan(FindCastellanQuery findCastellanQuery);
    CompletionStage findCastellans(FindCastellansQuery findCastellansQuery);
    CompletionStage existsCastellan(ExistenceCheckQuery existenceCheckQuery);

    CompletionStage findEnrollments(FindEnrollmentsQuery findEnrollmentsQuery);
    CompletionStage findUnitPlates(FindUnitPlatesQuery findUnitPlatesQuery);

    CompletionStage checkEnrolled(EnrolledCheckQuery enrolledCheckQuery);
}
