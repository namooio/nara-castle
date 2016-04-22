package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.service.CastellanService;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;

public class CastellanServiceLogic implements CastellanService {
    //
    private CastellanStore castellanStore;

    public CastellanServiceLogic(CastleStoreLycler storeLycler) {
        //
        this.castellanStore = storeLycler.requestCastellanStore();
    }

    @Override
    public Castellan findCastellan(String id) {
        //
        return castellanStore.retrieve(id);
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
        String existingPhotoId = castellan.getPhotoId();

        // Existing photo id could be null.
        if (existingPhotoId != null && existingPhotoId.equals(photoId)) {
            return;
        }

        castellan.setPhotoId(photoId);
        castellanStore.update(castellan);
    }

    @Override
    public void modifyPrimaryEmail(String id, String email) {
        //
        Castellan castellan = castellanStore.retrieve(id);
        String existingPrimaryEmail = castellan.getPrimaryEmail();

        // Existing primary email could be null.
        if (existingPrimaryEmail != null && existingPrimaryEmail.equals(email)) {
            return;
        }

        castellan.setPrimaryEmail(email);
        castellanStore.update(castellan);
    }

    @Override
    public void modifyPrimaryPhone(String id, String phoneNumber) {
        //
        Castellan castellan = castellanStore.retrieve(id);
        String existingPrimaryPhone = castellan.getPrimaryPhone();

        // Existing primary phone could be null.
        if (existingPrimaryPhone != null && existingPrimaryPhone.equals(phoneNumber)) {
            return;
        }

        castellan.setPrimaryPhone(phoneNumber);
        castellanStore.update(castellan);
    }
}