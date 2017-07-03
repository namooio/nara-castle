package namoo.nara.castle.cp.akka.actor.util;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.util.Timeout;
import namoo.nara.share.domain.protocol.NaraProtocol;
import namoo.nara.share.exception.NaraException;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class AwaitableActorExecutor<T> {
    //
    private static final Timeout timeout = new Timeout(Duration.create(5, "seconds"));

    public T execute(ActorRef actorRef, NaraProtocol protocol) {
        //
        Future<Object> future = Patterns.ask(actorRef, protocol, timeout);
        try {
            return (T) Await.result(future, timeout.duration());
        } catch (Exception e) {
            throw new NaraException(e);
        }
    }

}
