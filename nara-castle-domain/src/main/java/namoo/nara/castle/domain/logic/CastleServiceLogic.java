package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.*;
import namoo.nara.castle.domain.entity.contact.UserEmail;
import namoo.nara.castle.domain.entity.contact.UserName;
import namoo.nara.castle.domain.entity.history.AccountBook;
import namoo.nara.castle.domain.entity.history.MetroBook;
import namoo.nara.castle.domain.entity.history.ParticipantMetro;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.store.*;
import namoo.nara.share.exception.NaraException;

import java.util.List;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 1..
 */
public class CastleServiceLogic implements CastleService {

    private CastleStore castleStore;

    private CastellanStore castellanStore;

    private CastellanEmailStore castellanEmailStore;

    private CastellanNameStore castellanNameStore;

    public CastleServiceLogic(CastleStoreLycler lifecycler) {
        this.castleStore = lifecycler.requestCastleStore();
        this.castellanStore = lifecycler.requestCastellanStore();
        this.castellanEmailStore = lifecycler.requestCastellanEmailStore();
        this.castellanNameStore = lifecycler.requestCastellanNameStore();
    }

    @Override
    public void buildCastle(String usid, String name, String metroId, Locale locale) {
        //
        Castle castle = Castle.newInstance(usid, name, locale);
        MetroBook metroBook = castle.getInfoBundleBox().getHistoryBundle().getMetroBook();
        metroBook.addMetro(new ParticipantMetro(metroId, castle.getBuildTime()));

        castleStore.create(castle);     // Castellan을 별도로 생성 ???
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
