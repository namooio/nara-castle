package namoo.nara.castle.actor;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.actor.UntypedActor;

/**
 * Created by hkoo on 2017. 7. 5..
 */
public class CastleHelloActor extends UntypedActor {
    //

    public static Props props() {
        return Props.create(CastleHelloActor.class);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        //
        if (message instanceof String) {
            System.out.println("Received at CastleHelloActor: " + Thread.currentThread().getName());
            sender().tell("Hello, " + message, self());
        }
    }
}
