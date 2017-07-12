package namoo.nara.castle.cp.play;

import akka.actor.ActorSystem;

import javax.inject.Singleton;

enum CastleActorSystem {
    //
    INSTANCE;


    final private ActorSystem actorSystem;


    CastleActorSystem() {
        this.actorSystem = ActorSystem.create();
    }

    @Singleton
    public ActorSystem getActorSystem() {
        return actorSystem;
    }

}
