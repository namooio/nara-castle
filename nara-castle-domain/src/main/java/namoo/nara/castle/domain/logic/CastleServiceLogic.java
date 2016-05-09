package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.InfoBundleBox;
import namoo.nara.castle.domain.entity.OpenState;
import namoo.nara.castle.domain.entity.contact.ContactBundle;
import namoo.nara.castle.domain.entity.contact.UserEmail;
import namoo.nara.castle.domain.entity.history.CastleState;
import namoo.nara.castle.domain.entity.history.HistoryBundle;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.store.*;

import java.util.List;
import java.util.Locale;

public class CastleServiceLogic implements CastleService {
    //
    private CastleStore castleStore;
    private CastellanStore castellanStore;
    private HistoryBundleStore historyStore;
    private ContactBundleStore contactBundleStore;

    public CastleServiceLogic(CastleStoreLycler storeLycler) {
        //
        this.castleStore = storeLycler.requestCastleStore();
        this.castellanStore = storeLycler.requestCastellanStore();
        this.historyStore = storeLycler.requestHistoryBundleStore();
        this.contactBundleStore = storeLycler.requestContactBundleStore();
    }

    @Override
    public void buildCastle(String id, String name, String email, Locale locale) {
        //
        Castle castle = Castle.newInstance(id, name, email, locale);

        InfoBundleBox bundleBox = castle.getInfoBundleBox();
        HistoryBundle history = bundleBox.getHistoryBundle();
        ContactBundle contact = bundleBox.getContactBundle();
        contact.getEmailBook().addEmail(new UserEmail(email));

        castleStore.create(castle);     // Castellan을 별도로 생성 ???
        castellanStore.create(castle.getOwner());
        historyStore.create(history);
        contactBundleStore.create(contact);
    }

    @Override
    public void suspendCastle(String id, String remarks) {
        //
        Castle castle = castleStore.retrieve(id);

        if (castle.getState().equals(OpenState.Suspended)) {
            return;
        }

        HistoryBundle history = historyStore.retrieve(id);
        OpenState currentState = castle.getState();

        castle.setState(OpenState.Suspended);
        castleStore.update(castle);

        OpenState targetState = OpenState.Suspended;
        CastleState castleState = new CastleState(currentState, targetState, remarks);
        history.getCastleStateBook().attachCastleState(castleState);
        historyStore.updateCastleStateBook(history);
    }

    @Override
    public void reopenCastle(String id, String remarks) {
        //
        Castle castle = castleStore.retrieve(id);

        if (castle.getState().equals(OpenState.Open)) {
            return;
        }

        HistoryBundle history = historyStore.retrieve(id);
        OpenState currentState = castle.getState();

        castle.setState(OpenState.Open);
        castleStore.update(castle);

        OpenState targetState = OpenState.Open;
        CastleState castleState = new CastleState(currentState, targetState, remarks);
        history.getCastleStateBook().attachCastleState(castleState);
        historyStore.updateCastleStateBook(history);
    }

    @Override
    public void modifyName(String id, String name) {
        //
        Castle castle = castleStore.retrieve(id);

        if(castle.getName().equals(name)) {
            return;
        }

        castle.setName(name);
        castleStore.update(castle);
    }

    @Override
    public void modifyLocale(String id, Locale locale) {
        //
        Castle castle = castleStore.retrieve(id);

        if(castle.getLocale().equals(locale)) {
            return;
        }

        castle.setLocale(locale);
        castleStore.update(castle);
    }

    @Override
    public Castle findCastle(String id) {
        //
        return castleStore.retrieve(id);
    }

    @Override
    public List<Castle> findAllCastles() {
        //
        return castleStore.retrieveAll();
    }

}
