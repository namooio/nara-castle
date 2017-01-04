package namoo.nara.castle.es.handler.internal;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.event.CastleBuiltEvent;
import namoo.nara.share.event.NaraEventHandler;

public class CastleBuiltEventHandler implements NaraEventHandler<CastleBuiltEvent> {
    //
    @Override
    public void handle(CastleBuiltEvent castleBuiltEvent) {
        //
        CastleService castleService = CastleContext.getServiceLycler().requestCastleService();

        String castleId = castleBuiltEvent.getCastleId();
        String metroId = castleBuiltEvent.getOriginMetroId();
        String citizenId = castleBuiltEvent.getOriginCitizenId();

        Castle castle = castleService.findCastle(castleId);
        if (castle.getCastellan().isJoinedMetro(metroId)) return;
        castleService.addJoinedMetro(castleId, metroId, citizenId);
    }
}
