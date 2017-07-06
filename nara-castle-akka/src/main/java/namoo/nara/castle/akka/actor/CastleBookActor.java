package namoo.nara.castle.akka.actor;

import akka.actor.Props;
import namoo.nara.castle.domain.context.CastleIdBuilder;
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

    static public Props props() {
        //
        return Props.create(CastleBookActor.class, () -> new CastleBookActor());
    }

    public CastleBookActor() {
        //
        super(new CastleBook(), CastleIdBuilder.makeCastleBookId());
    }

    @Override
    public void handleEvent(NaraEvent event) {
        //
        if (event instanceof SequenceIncreased) {
            handleSequenceIncreasedEvent((SequenceIncreased) event);
        }
    }

    @Override
    public void onReceiveCommand(NaraCommand command) {
        //
        if (command instanceof NextSequenceCommand) {
            handleNextSequenceCommand((NextSequenceCommand) command);
        }
    }

    @Override
    public void onReceiveQuery(NaraQuery query) {
        //
        if (query instanceof FindCastleBookQuery) {
            handleFindCastleBookQuery((FindCastleBookQuery) query);
        }
    }

    /*********************** Command ***********************/

    private void handleNextSequenceCommand(NextSequenceCommand command) {
        //
        long nextSequence = getState().nextSequence();

        persist(new SequenceIncreased(getState()), this::handleSequenceIncreasedEvent);
        getSender().tell(nextSequence, getSelf());
    }

    /*********************** Command ***********************/

    /*********************** Query ***********************/

    private void handleFindCastleBookQuery(FindCastleBookQuery query) {
        //
        getSender().tell(getState(), getSelf());
    }

    /*********************** Query ***********************/


    /*********************** Event ***********************/

    private void handleSequenceIncreasedEvent(SequenceIncreased event) {
        //
        getState().apply(event);
    }

    /*********************** Event ***********************/

}
