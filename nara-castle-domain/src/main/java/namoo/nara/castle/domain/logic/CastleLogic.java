package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.event.CastleEvent;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.sdo.CastleCdo;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.event.LycleType;
import namoo.nara.share.event.NaraEventProxy;
import namoo.nara.share.exception.NaraException;

import java.util.List;

public class CastleLogic implements CastleService {
    //
    private CastleStore castleStore;
    private NaraEventProxy eventProxy;

    public CastleLogic(CastleStoreLycler storeLycler, CastleProxyLycler proxyLycler) {
        this.castleStore = storeLycler.requestCastleStore();
        this.eventProxy = proxyLycler.eventProxy();
    }

    @Override
    public String buildCastle(CastleCdo castleCdo) {
        //
        String nationId = castleCdo.getNationId();
        String metroId = castleCdo.getMetroId();
        String civilianId = castleCdo.getCivilianId();
        String email = castleCdo.getEmail();

        long sequence = nextCastleSequence();

        String castleId = CastleContext.getCastleIdBuilder().makeCastleId(sequence);
        Castle castle = new Castle(castleId, nationId, metroId, civilianId, email);

        if (!castle.getCastellan().hasEmails()) {
            throw new NaraException("Castellan must have email.");
        }
        castleStore.create(castle);

        eventProxy.create(new CastleEvent(LycleType.Created, castleId));
        return castle.getId();
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
    public Castle findCastleByJoinedMetro(String nationId, String metroId, String civilianId) {
        //
        return castleStore.retrieveByJoinedMetro(nationId, metroId, civilianId);
    }

    @Override
    public List<Castle> findCastlesOf(String nationId) {
        //
        return castleStore.retrieveByNationId(nationId);
    }

    @Override
    public void modifyCastle(String castleId, NameValueList nameValues) {
        //
        Castle castle = castleStore.retrieve(castleId);
        castle.setValues(nameValues);

        if (!castle.getCastellan().hasEmails()) {
            throw new NaraException("Castellan must have email.");
        }
        castleStore.update(castle);
    }

    @Override
    public void removeCastle(String castleId) {
        //
        castleStore.delete(castleId);
    }

    private long nextCastleSequence() {
        //
        long nextSequence = 0L;

        CastleBook castleBook = castleStore.retrieveBook(CastleBook.class.getSimpleName());
        if(castleBook == null) {
            castleBook = new CastleBook();
            castleStore.createBook(new CastleBook());
        }

        nextSequence = castleBook.nextSequence();
        castleStore.updateBook(castleBook);

        return nextSequence;
    }
}