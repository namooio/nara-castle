package namoo.nara.castle.es.handler.internal;

import namoo.nara.castle.event.CastleBuiltEvent;
import namoo.nara.castle.spec.CastleService;
import namoo.nara.castle.spec.sdo.JoinedMetroCdo;
import namoo.nara.share.event.NaraEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CastleBuiltEventForJoinedMetroHandler implements NaraEventHandler<CastleBuiltEvent> {
    //
    @Autowired
    @Qualifier("castleServiceLogic")
    private CastleService castleService;

    @Override
    public void handle(CastleBuiltEvent castleBuiltEvent) {
        //
        String castleId = castleBuiltEvent.getCastleId();
        String metroId = castleBuiltEvent.getOriginMetroId();
        String citizenId = castleBuiltEvent.getOriginCitizenId();

        if (castleService.isJoinedMetro(castleId, metroId)) return;
        castleService.addJoinedMetro(castleId, new JoinedMetroCdo(metroId, citizenId));
    }
}
