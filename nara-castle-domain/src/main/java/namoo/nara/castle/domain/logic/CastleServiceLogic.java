package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.JoinedMetro;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.data.CastleCdo;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.event.CastleBuiltEvent;
import namoo.nara.share.event.NaraEventProxy;

import java.util.List;
import java.util.Locale;

public class CastleServiceLogic implements CastleService {
    //
    private CastleStore castleStore;
    private NaraEventProxy eventProxy;

    public CastleServiceLogic(CastleStoreLycler storeLycler, CastleProxyLycler proxyLycler) {
        //
        this.castleStore = storeLycler.requestCastleStore();
        this.eventProxy = proxyLycler.requestNaraEventProxy();
    }

    @Override
    public String buildCastle(CastleCdo castleCdo) {
        //
        String castellanEmail = castleCdo.getCastellanEmail();
        String originMetroId = castleCdo.getOriginMetroId();
        String originCitizenId = castleCdo.getOriginCitizenId();
        long sequence = castleStore.retrieveNextSequence();
        Castle castle = Castle.newInstance(sequence, castellanEmail, originMetroId, originCitizenId, castleCdo.getLocale());
        castleStore.create(castle);

        eventProxy.create(new CastleBuiltEvent(castle.getId(), castellanEmail, originMetroId, originCitizenId));
        return castle.getId();
    }

    @Override
    public void modifyLocale(String id, Locale locale) {
        //
        Castle castle = castleStore.retrieve(id);
        castle.setLocale(locale);
        castleStore.update(castle);
    }

    @Override
    public Castle findCastle(String id) {
        //
        return castleStore.retrieve(id);
    }

    @Override
    public Castle findCastleByEmail(String email) {
        //
        return castleStore.retrieveByEmail(email);
    }

    @Override
    public List<Castle> findCastles() {
        //
        return castleStore.retrieveAll();
    }

    @Override
    public void addEmail(String castleId, String email) {
        //
        Castle castle = castleStore.retrieve(castleId);
        Castellan castellan = castle.getCastellan();
        castellan.addEmail(email);
        castleStore.update(castle);
    }

    @Override
    public void removeEmail(String castleId, String email) {
        //
        Castle castle = castleStore.retrieve(castleId);
        Castellan castellan = castle.getCastellan();
        castellan.removeEmail(email);
        castleStore.update(castle);
    }

    @Override
    public void addJoinedMetro(String castleId, String metroId, String citizenId) {
        //
        Castle castle = castleStore.retrieve(castleId);
        Castellan castellan = castle.getCastellan();
        castellan.addJoinedMetro(metroId, citizenId);
        castleStore.update(castle);
    }

    @Override
    public List<JoinedMetro> findJoinedMetros(String castleId) {
        //
        Castle castle = castleStore.retrieve(castleId);
        Castellan castellan = castle.getCastellan();
        return castellan.getJoinedMetros();
    }

    @Override
    public void removeJoinedMetro(String castleId, String metroId) {
        //
        Castle castle = castleStore.retrieve(castleId);
        Castellan castellan = castle.getCastellan();
        castellan.removeJoinedMetro(metroId);
        castleStore.update(castle);
    }
}
