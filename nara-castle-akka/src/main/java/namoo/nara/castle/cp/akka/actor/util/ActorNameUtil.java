package namoo.nara.castle.cp.akka.actor.util;

public class ActorNameUtil {
    //
    public static String requestPersistentActorName(String id, Class entityClass) {
        //
        return String.format("%s-%s", entityClass.getSimpleName().toLowerCase(), id);
    }
}
