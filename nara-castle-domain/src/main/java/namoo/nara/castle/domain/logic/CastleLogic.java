package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.sdo.CastleCdo;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.event.NaraEventProxy;

import java.util.List;

public class CastleLogic implements CastleService {

    private CastleStore castleStore;
    private NaraEventProxy eventProxy;

    public CastleLogic(CastleStoreLycler storeLycler, CastleProxyLycler proxyLycler) {
        this.castleStore = storeLycler.requestCastleStore();
        this.eventProxy = proxyLycler.eventProxy();
    }

    @Override
    public String buildCastle(CastleCdo castleCdo) {

        String nationId = castleCdo.getNationId();
        String originMetroId = castleCdo.getOriginMetroId();
        String originCivilianId = castleCdo.getOriginCivilianId();
        NameValueList nameValues = castleCdo.getNameValues();

        long sequence = castleStore.retrieveNextSequence();

        String castleId = CastleContext.getCastleIdBuilder().makeCastleId(nationId, sequence);
        Castle castle = new Castle(castleId, nationId, originMetroId, originCivilianId);
        castle.setValues(nameValues);

        castleStore.create(castle);
        return castle.getId();
    }

    @Override
    public Castle findCastle(String id) {

        return castleStore.retrieve(id);
    }

    @Override
    public Castle findCastleByEmail(String email) {

        return castleStore.retrieveByEmail(email);
    }

    @Override
    public List<Castle> findCastles() {

        return castleStore.retrieveAll();
    }

    @Override
    public void modifyCastle(String castleId, NameValueList nameValues) {

        Castle castle = castleStore.retrieve(castleId);
        castle.setValues(nameValues);
        castleStore.update(castle);
    }

    @Override
    public void removeCastle(String castleId) {

        castleStore.delete(castleId);
    }
}
