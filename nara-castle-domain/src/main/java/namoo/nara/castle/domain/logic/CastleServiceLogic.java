package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.data.CastleCdo;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;

import java.util.List;
import java.util.Locale;

public class CastleServiceLogic implements CastleService {
    //
    private CastleStore castleStore;

    public CastleServiceLogic(CastleStoreLycler storeLycler) {
        //
        this.castleStore = storeLycler.requestCastleStore();
    }

    @Override
    public String buildCastle(CastleCdo castleCdo) {
        //
        long sequence = castleStore.retrieveNextSequence();
        Castle castle = Castle.newInstance(sequence, castleCdo.getLocale());
        castleStore.create(castle);

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
    public List<Castle> findCastles() {
        //
        return castleStore.retrieveAll();
    }

}
