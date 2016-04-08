package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.OpenState;
import namoo.nara.castle.domain.entity.history.CastleState;
import namoo.nara.castle.domain.entity.history.HistoryBundle;
import namoo.nara.castle.domain.entity.history.ParticipantMetro;
import namoo.nara.castle.domain.service.CastellanService;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.domain.store.HistoryBundleStore;

import java.util.Locale;

public class CastellanServiceLogic implements CastellanService {
    //
    private CastellanStore castellanStore;

    public CastellanServiceLogic(CastleStoreLycler storeLycler) {
        //
        this.castellanStore = storeLycler.requestCastellanStore();
    }

    @Override
    public void modifyDisplayName(String id, String displayName) {
        //
        Castellan castellan = castellanStore.retrieve(id);
        if (castellan.getDisplayName().equals(displayName)) {
            return;
        }

        castellan.setDisplayName(displayName);
        castellanStore.update(castellan);
    }

    @Override
    public void modifyPhoto(String id, String photoId) {
        //
        Castellan castellan = castellanStore.retrieve(id);
        if (castellan.getPhotoId().equals(photoId)) {
            return;
        }

        castellan.setPhotoId(photoId);
        castellanStore.update(castellan);
    }

    @Override
    public void modifyPrimaryEmail(String id, String email) {
        //
        Castellan castellan = castellanStore.retrieve(id);
        if (castellan.getPrimaryEmail().equals(email)) {
            return;
        }

        castellan.setPrimaryEmail(email);
        castellanStore.update(castellan);
    }

    @Override
    public void modifyPrimaryPhone(String id, String phoneNumber) {
        //
        Castellan castellan = castellanStore.retrieve(id);
        if (castellan.getPrimaryPhone().equals(phoneNumber)) {
            return;
        }

        castellan.setPrimaryPhone(phoneNumber);
        castellanStore.update(castellan);
    }
}