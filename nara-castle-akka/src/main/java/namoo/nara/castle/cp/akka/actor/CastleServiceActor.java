package namoo.nara.castle.cp.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.share.domain.event.NaraEvent;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.domain.protocol.NaraQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleServiceActor extends AbstractActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleService castleService;

    static public Props props(CastleService castleService) {
        //
        return Props.create(CastleServiceActor.class, () -> new CastleServiceActor(castleService));
    }

    public CastleServiceActor(CastleService castleService) {
        //
        this.castleService = castleService;
    }

    @Override
    public Receive createReceive() {
        //
        return receiveBuilder()
                .match(NaraCommand.class, this::handleCommand)
                .match(NaraQuery.class, this::handleQuery)
                .build();
    }

    private void handleCommand(NaraCommand command) {
        //
        logger.debug("Handle command start  {}{}", command.getClass().getSimpleName(), command);

//        if (command instanceof AccountOwnerRegister) handleAccountOwnerRegister((AccountOwnerRegister) command);
//        else if (command instanceof AccountOwnerModify) handleAccountOwnerModify((AccountOwnerModify) command);
//        else if (command instanceof AccountOwnerRemove) handleAccountOwnerRemove((AccountOwnerRemove) command);
//        else {
//            logger.debug("No command handler found {}", command.getClass().getSimpleName());
//        }

        logger.debug("Handle command finish {}{}", command.getClass().getSimpleName(), command);
    }

    private void handleQuery(NaraQuery query) {
        //
        logger.debug("Handle query start  {}{}", query.getClass().getSimpleName(), query);

//        if (query instanceof AccountOwnerFind) handleAccountOwnerFind((AccountOwnerFind) query);
//        else if (query instanceof AccountOwnerFindByUsername) handleAccountOwnerFindByUsername((AccountOwnerFindByUsername) query);
//        else if (query instanceof AccountOwnerFindByEmail) handleAccountOwnerFindByEmail((AccountOwnerFindByEmail) query);
//        else if (query instanceof ExistsAccountOwner) handleExistsAccountOwner((ExistsAccountOwner) query);
//        else if (query instanceof ExistsUsernameAccount) handleExistsUsernameAccount((ExistsUsernameAccount) query);
//        else if (query instanceof ExistsEmailAccount) handleExistsEmailAccount((ExistsEmailAccount) query);
//        else {
//            logger.debug("No query handler found {}", query);
//        }

        logger.debug("Handle query finish {}{}", query.getClass().getSimpleName(), query);
    }

    private void handleEvent(NaraEvent event) {
        //
        logger.debug("Handle event start  {}{}", event.getClass().getSimpleName(), event);
//        throw new NaraException("boom");
        logger.debug("Handle event finish {}{}", event.getClass().getSimpleName(), event);
    }
}
