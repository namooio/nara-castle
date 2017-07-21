import com.google.inject.AbstractModule;
import nara.castle.cp.CastleRMMongoStoreLycler;
import nara.castle.cp.CastleRMProjectionLycler;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import play.Logger;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {
    //
    final Logger.ALogger logger = Logger.of(this.getClass());

    @Override
    public void configure() {
        // Use the system clock as the default implementation of Clock
//        bind(Clock.class).toInstance(Clock.systemDefaultZone());

        // Ask Guice to create an instance of ApplicationTimer when the
        // application starts.

        bind(CastleRMStoreLycler.class).to(CastleRMMongoStoreLycler.class).asEagerSingleton();
        bind(CastleRMProjectionLycler.class).asEagerSingleton();
    }

}
