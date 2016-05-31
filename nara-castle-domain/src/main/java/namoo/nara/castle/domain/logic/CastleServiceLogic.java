package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.InfoBundleBox;
import namoo.nara.castle.domain.entity.OpenState;
import namoo.nara.castle.domain.entity.contact.ContactBundle;
import namoo.nara.castle.domain.entity.contact.UserEmail;
import namoo.nara.castle.domain.entity.history.CastleState;
import namoo.nara.castle.domain.entity.history.HistoryBundle;
import namoo.nara.castle.domain.entity.history.LoginAccount;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.proxy.GatewayProxy;
import namoo.nara.castle.domain.service.CastleCdo;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.domain.store.ContactBundleStore;
import namoo.nara.castle.domain.store.HistoryBundleStore;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class CastleServiceLogic implements CastleService {
    //
    private CastleStore castleStore;
    private CastellanStore castellanStore;
    private HistoryBundleStore historyStore;
    private ContactBundleStore contactBundleStore;

    private GatewayProxy gatewayProxy;

    public CastleServiceLogic(CastleStoreLycler storeLycler, CastleProxyLycler proxyLycler) {
        //
        this.castleStore = storeLycler.requestCastleStore();
        this.castellanStore = storeLycler.requestCastellanStore();
        this.historyStore = storeLycler.requestHistoryBundleStore();
        this.contactBundleStore = storeLycler.requestContactBundleStore();

        this.gatewayProxy = proxyLycler.requestGatewayProxy();
    }

    @Override
    public void buildCastle(CastleCdo castleCdo) {
        //
        String id = castleCdo.getId();
        String name = castleCdo.getName();
        String email = castleCdo.getEmail();
        Locale locale = castleCdo.getLocale();
        String password = castleCdo.getPassword();

        Castle castle = Castle.newInstance(id, name, email, locale);

        InfoBundleBox bundleBox = castle.getInfoBundleBox();
        HistoryBundle history = bundleBox.getHistoryBundle();
        history.getAccountBook().addAccount(LoginAccount.newInstance(email, LoginAccount.LoginChannel.NaraEmail));
        history.getAccountBook().addAccount(LoginAccount.newInstance(name, LoginAccount.LoginChannel.NaraUsername));

        ContactBundle contact = bundleBox.getContactBundle();
        contact.getEmailBook().addEmail(new UserEmail(email));
        // username?
//        contact.getNameBook().add(new UserName());

        castleStore.create(castle);     // Castellan을 별도로 생성 ???
        castellanStore.create(castle.getOwner());
        historyStore.create(history);
        contactBundleStore.create(contact);

        gatewayProxy.createNaraAccount(id, name, email, password);
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
        Castle castle = castleStore.retrieve(id);
        Castellan castellan = castellanStore.retrieve(id);

        castle.setOwner(castellan);
        return castle;
    }

    @Override
    public List<Castle> findAllCastles() {
        //
        List<Castle> allCastles = castleStore.retrieveAll();

        List<Castellan> allCastellans = castellanStore.retrieveAll();
        Map<String, Castellan> allCastellansMap = allCastellans.stream()
                .collect(Collectors.toMap(Castellan::getId, castellan -> castellan));

        allCastles.forEach(castle -> castle.setOwner(allCastellansMap.get(castle.getId())));

        return allCastles;
    }

}
