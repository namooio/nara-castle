package nara.castle.actor.akka.command;

import akka.actor.Props;
import nara.castle.domain.castlebook.entity.CastleBook;
import nara.castle.domain.castlebook.command.NextSequenceCommand;
import nara.castle.domain.castlebook.event.SequenceIncreased;
import nara.castle.domain.castlequery.query.FindCastleBookQuery;
import nara.share.actor.akka.NaraPersistentActor;
import nara.share.domain.event.NaraEvent;
import nara.share.domain.protocol.NaraCommand;
import nara.share.domain.protocol.NaraQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleBookActor extends NaraPersistentActor<CastleBook> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    static public Props props(String castleBookId) {
        //
        return Props.create(CastleBookActor.class, () -> new CastleBookActor(castleBookId));
    }

    public CastleBookActor(String castleBookId) {
        //
        super(new CastleBook(castleBookId));
    }

    @Override
    public void handleEvent(NaraEvent event) {
        //
        matcher()
            .match(SequenceIncreased.class, sequenceIncreased -> getState().apply(sequenceIncreased))
        .onMessage(event);
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        matcher()
            .match(NextSequenceCommand.class, nextSequenceCommand -> {
                //
                getState().nextSequence();
                persist(new SequenceIncreased(getState()), this::handleAndRespond);
            })
        .onMessage(command);
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        matcher()
            .match(FindCastleBookQuery.class, findCastleBookQuery -> responseStateResult())
        .onMessage(query);
    }

}
