package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.InfoBundleBox;
import namoo.nara.castle.domain.entity.OpenState;
import namoo.nara.castle.domain.entity.contact.ContactBundle;
import namoo.nara.castle.domain.entity.contact.UserEmail;
import namoo.nara.castle.domain.entity.history.CastleState;
import namoo.nara.castle.domain.entity.history.HistoryBundle;
import namoo.nara.castle.domain.entity.history.ParticipantMetro;
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
    public void buildCastle(String id, String name, String email, Locale locale, String metroId) {
        //
        buildCastle(id, name, email, locale);

        HistoryBundle history = historyStore.retrieve(id);
        history.getMetroBook().addMetro(new ParticipantMetro(metroId, System.currentTimeMillis()));
        historyStore.updateMetroBook(history);
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

    /*
    @Override
    public void registerCastellan(String castellanId) {
        Castle castle = new Castle(castellanId, CastleState.Open);
        castleStore.create(castle);
        Castellan castellan = new Castellan(castellanId);
        castellanStore.create(castellan);
    }

    @Override
    public void registerCastellan(String castellanId, String email) {
        // Create castle. Castle is suspended before email verification...(can't login)
        Castle castle = new Castle(castellanId, CastleState.Suspended);
        castleStore.create(castle);

        Castellan castellan = new Castellan(castellanId);
        castellanStore.create(castellan);

        // Add castellan email. Email is not verified yet.
        addCastellanEmail(email, castellanId);
    }

    @Override
    public void removeCastellan(String castellanId) {
        castellanEmailStore.deleteByCastellanId(castellanId);
        castellanNameStore.deleteByCastellanId(castellanId);
        castellanStore.delete(castellanId);
        castleStore.delete(castellanId);
    }

    @Override
    public Castellan findCastellan(String castellanId) {
        return castellanStore.retrieve(castellanId);
    }

    @Override
    public Castellan findCastellanByVerifiedEmail(String email) {
        String castellanOid = castellanEmailStore.retrieveCastellanIdByVerifiedEmail(email);
        if (castellanOid == null) return null;
        return castellanStore.retrieve(castellanOid);
    }

    @Override
    public void addCastellanEmail(String email, String castellanId) {
        UserEmail castellanEmail = new UserEmail();
        castellanEmail.setEmail(email);
        castellanEmailStore.create(castellanEmail, castellanId);

        // If castellan hasn't primary email. Set this email as primary
        if (!castellanEmailStore.hasPrimaryEmail(castellanId)) {
            setAsPrimaryEmail(castellanEmail.getEmail(), castellanId);
        }
    }

    @Override
    public void verifyCastellanEmail(String email, String castellanId) {
        if (castellanEmailStore.existVerifiedEmail(email)) throw new NaraException("Email is already in use");
        UserEmail castellanEmail = castellanEmailStore.retrieve(email, castellanId);
        castellanEmail.setVerified(true);
        castellanEmail.setVerifiedTime(System.currentTimeMillis());
        castellanEmailStore.update(castellanEmail, castellanId);
    }

    @Override
    public void setAsPrimaryEmail(String email, String castellanId) {
        UserEmail castellanEmail = castellanEmailStore.retrieve(email, castellanId);
        // Already primary... ignore
        if (castellanEmail.isPrimary()) return;

        // Update exist primary email as not.
        if (castellanEmailStore.hasPrimaryEmail(castellanId)) {
            UserEmail primaryEmail = castellanEmailStore.getPrimaryEmail(castellanId);
            castellanEmailStore.updatePrimaryEmail(primaryEmail.getEmail(), castellanId, false);
        }
        castellanEmailStore.updatePrimaryEmail(castellanEmail.getEmail(), castellanId, true);
    }

    @Override
    public void addName(UserName castellanName, String castellanId) {
        // TODO
    }

    @Override
    public void removeCastellanEmail(String email, String castellanId) {
        // At least one email is required.
        if (castellanEmailStore.countCastellanEmail(castellanId) <= 1) throw new NaraException("At least one email is required.");

        // Delete castellan email
        castellanEmailStore.delete(email, castellanId);

        // If there is no primary email, set the first email as primary
        if (castellanEmailStore.hasPrimaryEmail(castellanId)) return;
        List<UserEmail> castellanEmails = castellanEmailStore.retrieveCastellanEmails(castellanId);
        UserEmail firstEmail = castellanEmails.get(0);
        setAsPrimaryEmail(firstEmail.getEmail(), castellanId);
    }

    @Override
    public void changeCastleStatus(CastleState status, String castellanId) {
        Castle castle = castleStore.retrieve(castellanId);
        castle.setStatus(status);
        castleStore.update(castle);
    }

    @Override
    public String findCastellanDisplayName(String castellanId) {
        Castellan castellan = castellanStore.retrieve(castellanId);
        return castellan.getDisplayName();
    }
    */
}
