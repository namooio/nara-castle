package namoo.nara.castle.akka.actor.persistence;

import akka.actor.Props;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.event.castlebook.SequenceIncreased;
import namoo.nara.castle.domain.spec.query.castlebook.FindCastleBookQuery;
import namoo.nara.share.akka.support.actor.NaraPersistentActor;
import namoo.nara.share.domain.event.NaraEvent;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.domain.protocol.NaraQuery;
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
        match()
            .with(SequenceIncreased.class, sequenceIncreased -> getState().apply(sequenceIncreased))
        .onMessage(event);
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        match()
            .with(NextSequenceCommand.class, nextSequenceCommand -> {
                //
                getState().nextSequence();
                persist(new SequenceIncreased(getState()), this::handleEventAndRespond);
            })
        .onMessage(command);
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        match()
            .with(FindCastleBookQuery.class, findCastleBookQuery -> responseStateResult())
        .onMessage(query);
    }

}
