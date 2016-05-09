package namoo.nara.castle.sp.jaxrs.jersey;

import namoo.nara.castle.sp.jaxrs.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 26..
 */
@Component
public class JerseyConfig extends ResourceConfig {
    //
    public JerseyConfig() {
        //
        register(CastleRepResource.class);
        register(CastleFrontResource.class);
        register(CastleHistoryFrontResource.class);
        register(CastellanFrontResource.class);
        register(CastellanContactFrontResource.class);
    }
}
