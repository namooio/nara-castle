package namoo.nara.castle.es.handler.external;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.share.event.NaraEventHandler;
import namoo.nara.town.event.CitizenRegisteredEvent;

public class CitizenRegisteredEventHandler implements NaraEventHandler<CitizenRegisteredEvent> {
    //
    private CastleServiceLycler serviceLycler = CastleContext.getServiceLycler();

    @Override
    public void handle(CitizenRegisteredEvent citizenRegisteredEvent) {
        //
        String metroId = citizenRegisteredEvent.getMetroId();
        String citizenId = citizenRegisteredEvent.getCitizenId();
        String castleId = citizenRegisteredEvent.getCastleId();

        serviceLycler.requestCastleService().addJoinedMetro(castleId, metroId, citizenId);
    }
}
