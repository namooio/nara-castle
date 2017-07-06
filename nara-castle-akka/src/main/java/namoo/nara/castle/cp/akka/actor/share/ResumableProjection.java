package namoo.nara.castle.cp.akka.actor.share;

import akka.persistence.query.Offset;

import java.util.concurrent.CompletionStage;

public class ResumableProjection {
    //
    public ResumableProjection() {
        //
    }

    public CompletionStage<Long> saveProgress(Offset offset) {
        // ...
        //#projection-into-different-store
        return null;
        //#projection-into-different-store
    }
    public CompletionStage<Long> latestOffset() {
        // ...
        //#projection-into-different-store
        return null;
        //#projection-into-different-store
    }
}
