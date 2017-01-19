package namoo.nara.castle.es.handler.internal;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.event.CastleBuiltEvent;
import namoo.nara.share.event.NaraEventHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class CastleBuiltEventHandler implements NaraEventHandler<CastleBuiltEvent> {
    //
    @Autowired
    private CastleService castleService;

    @Override
    public void handle(CastleBuiltEvent castleBuiltEvent) {
        //
        String castleId = castleBuiltEvent.getCastleId();
        String metroId = castleBuiltEvent.getOriginMetroId();
        String citizenId = castleBuiltEvent.getOriginCitizenId();

        Castle castle = castleService.findCastle(castleId);
        if (castle.getCastellan().isJoinedMetro(metroId)) return;
        castleService.addJoinedMetro(castleId, metroId, citizenId);
    }
}
