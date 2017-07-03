package namoo.nara.castle.cp.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import namoo.nara.castle.cp.akka.actor.util.AwaitableActorExecutor;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Aggregate
 */
public class CastleServiceActor extends AbstractActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

//    private CastleService castleService;

    static public Props props() {
        //
        return Props.create(CastleServiceActor.class, () -> new CastleServiceActor());
    }

    public CastleServiceActor() {
        //
    }

    @Override
    public Receive createReceive() {
        //
        return receiveBuilder()
                // command
                .match(EnrollMetroCommand.class, this::handleEnrollMetroCommand)
                .match(ModifyCastleCommand.class, this::handleModifyCastleCommand)

                // query
                .match(FindCastleQuery.class, this::handleFindCastleQuery)
                .build();
    }

    private void handleEnrollMetroCommand(EnrollMetroCommand command) {
        //
        logger.debug("Handle command start  {}[{}]", command.getClass().getSimpleName(), command);

        ActorRef castleBookActor = lookupOrCreateCastleBookActor();

        Long nextCastleSequence = new AwaitableActorExecutor<Long>().execute(castleBookActor, new NextSequenceCommand());
        String castleId = CastleIdBuilder.makeCastleId(nextCastleSequence);

        ActorRef castleActor = lookupCastleActor(castleId);
        if (castleActor == null) {
            MetroEnrollment enrollment = new MetroEnrollment(
                    command.getMetroId(),
                    command.getCivilianId(),
                    command.getName(),
                    command.getEmail(),
                    command.getZone());

            Castle castle = new Castle(castleId, enrollment);
            castleActor = createCastleActor(castle);
        }

        Castle castle = new AwaitableActorExecutor<Castle>().execute(castleActor, new FindCastleQuery(castleId));
        getSender().tell(castle, getSelf());

        logger.debug("Handle command finish {}[{}]", command.getClass().getSimpleName(), command);
    }

    private void handleModifyCastleCommand(ModifyCastleCommand command) {
        //
        logger.debug("Handle command start  {}[{}]", command.getClass().getSimpleName(), command);

        ActorRef castleActor = lookupCastleActor(command.getCastleId());
        castleActor.tell(command, getSelf());

        logger.debug("Handle command finish {}[{}]", command.getClass().getSimpleName(), command);
    }

    private void handleFindCastleQuery(FindCastleQuery query) {
        //
        logger.debug("Handle query start  {}[{}]", query.getClass().getSimpleName(), query);

        ActorRef castleActor = lookupCastleActor(query.getCastleId());

        Castle castle = new AwaitableActorExecutor<Castle>().execute(castleActor, query);
        getSender().tell(castle, getSelf());

        logger.debug("Handle query finish {}[{}]", query.getClass().getSimpleName(), query);
    }

    private ActorRef lookupOrCreateCastleBookActor() {
        //
        String castleBookId = CastleIdBuilder.makeCastleBookId();
        String name = getEntityActorName(castleBookId, CastleBookActor.class);

        Optional<ActorRef> child = getContext().findChild(name);
        if (child.isPresent()) return child.get();
        else {
            logger.info("Creating new {} castle book actor to handle a request for id {}", CastleBookActor.class.getSimpleName(), castleBookId);
            return getContext().actorOf(CastleBookActor.props(), name);
        }
    }

    private ActorRef lookupCastleActor(String castleId) {
        //
        String name = getEntityActorName(castleId, Castle.class);
        return getContext().findChild(name).orElse(null);
    }

    private ActorRef createCastleActor(Castle castle) {
        //
        String name = getEntityActorName(castle.getId(), Castle.class);

        logger.info("Creating new {} castle actor to handle a request for id {}", Castle.class.getSimpleName(), castle.getId());
        return getContext().actorOf(CastleActor.props(castle), name);
    }

//    private void fowardCommand(String id, Class entityClass, NaraCommand command) {
//        //
//        ActorRef entity = lookupOrCreateChild(id, entityClass);
//        entity.forward(command, context());
//    }

    private String getEntityActorName(String id, Class entityClass) {
        //
        return String.format("%s-%s", getEntityName(entityClass).toLowerCase(), id);
    }

    private String getEntityName(Class entityClass) {
        //
        return entityClass.getSimpleName();
    }

}
