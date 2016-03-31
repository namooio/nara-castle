package namoo.nara.castle.domain.service.logic;

import namoo.nara.castle.domain.entity.*;
import namoo.nara.castle.domain.lifecycler.CastleComponentLifecycler;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.store.CastellanEmailStore;
import namoo.nara.castle.domain.store.CastellanNameStore;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.shared.exception.NamooException;

import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 1..
 */
public class CastleServiceLogic implements CastleService {

    private CastleStore castleStore;

    private CastellanStore castellanStore;

    private CastellanEmailStore castellanEmailStore;

    private CastellanNameStore castellanNameStore;

    public CastleServiceLogic(CastleComponentLifecycler lifecycler) {
        this.castleStore = lifecycler.requestCastleStore();
        this.castellanStore = lifecycler.requestCastellanStore();
        this.castellanEmailStore = lifecycler.requestCastellanEmailStore();
        this.castellanNameStore = lifecycler.requestCastellanNameStore();
    }

    @Override
    public void registerCastellan(String castellanOid) {
        Castle castle = new Castle(castellanOid, CastleStatus.Opened);
        castleStore.create(castle);
        Castellan castellan = new Castellan(castellanOid);
        castellanStore.create(castellan);
    }

    @Override
    public void registerCastellan(String castellanOid, String email) {
        // Create castle. Castle is suspended before email verification...(can't login)
        Castle castle = new Castle(castellanOid, CastleStatus.Suspended);
        castleStore.create(castle);

        Castellan castellan = new Castellan(castellanOid);
        castellanStore.create(castellan);

        // Add castellan email. Email is not verified yet.
        addCastellanEmail(email, castellanOid);
    }

    @Override
    public void removeCastellan(String castellanOid) {
        castellanEmailStore.deleteByCastellanOid(castellanOid);
        castellanNameStore.deleteByCastellanOid(castellanOid);
        castellanStore.delete(castellanOid);
        castleStore.delete(castellanOid);
    }

    @Override
    public Castellan findCastellan(String castellanOid) {
        return castellanStore.retrieve(castellanOid);
    }

    @Override
    public Castellan findCastellanByVerifiedEmail(String email) {
        String castellanOid = castellanEmailStore.retrieveCastellanOidByVerifiedEmail(email);
        if (castellanOid == null) return null;
        return castellanStore.retrieve(castellanOid);
    }

    @Override
    public void addCastellanEmail(String email, String castellanOid) {
        CastellanEmail castellanEmail = new CastellanEmail();
        castellanEmail.setEmail(email);
        castellanEmailStore.create(castellanEmail, castellanOid);

        // If castellan hasn't primary email. Set this email as primary
        if (!castellanEmailStore.hasPrimaryEmail(castellanOid)) {
            setAsPrimaryEmail(castellanEmail.getEmail(), castellanOid);
        }
    }

    @Override
    public void verifyCastellanEmail(String email, String castellanOid) {
        if (castellanEmailStore.existVerifiedEmail(email)) throw new NamooException("Email is already in use");
        CastellanEmail castellanEmail = castellanEmailStore.retrieve(email, castellanOid);
        castellanEmail.setVerified(true);
        castellanEmail.setVerifiedTime(System.currentTimeMillis());
        castellanEmailStore.update(castellanEmail, castellanOid);
    }

    @Override
    public void setAsPrimaryEmail(String email, String castellanOid) {
        CastellanEmail castellanEmail = castellanEmailStore.retrieve(email, castellanOid);
        // Already primary... ignore
        if (castellanEmail.isPrimary()) return;

        // Update exist primary email as not.
        if (castellanEmailStore.hasPrimaryEmail(castellanOid)) {
            CastellanEmail primaryEmail = castellanEmailStore.getPrimaryEmail(castellanOid);
            castellanEmailStore.updatePrimaryEmail(primaryEmail.getEmail(), castellanOid, false);
        }
        castellanEmailStore.updatePrimaryEmail(castellanEmail.getEmail(), castellanOid, true);
    }

    @Override
    public void addName(CastellanName castellanName, String castellanOid) {
        // TODO
    }

    @Override
    public void removeCastellanEmail(String email, String castellanOid) {
        // At least one email is required.
        if (castellanEmailStore.countCastellanEmail(castellanOid) <= 1) throw new NamooException("At least one email is required.");

        // Delete castellan email
        castellanEmailStore.delete(email, castellanOid);

        // If there is no primary email, set the first email as primary
        if (castellanEmailStore.hasPrimaryEmail(castellanOid)) return;
        List<CastellanEmail> castellanEmails = castellanEmailStore.retrieveCastellanEmails(castellanOid);
        CastellanEmail firstEmail = castellanEmails.get(0);
        setAsPrimaryEmail(firstEmail.getEmail(), castellanOid);
    }

    @Override
    public void changeCastleStatus(CastleStatus status, String castellanOid) {
        Castle castle = castleStore.retrieve(castellanOid);
        castle.setStatus(status);
        castleStore.update(castle);
    }

    @Override
    public String findCastellanDisplayName(String castellanOid) {
        Castellan castellan = castellanStore.retrieve(castellanOid);
        return castellan.getDisplayName();
    }
}
