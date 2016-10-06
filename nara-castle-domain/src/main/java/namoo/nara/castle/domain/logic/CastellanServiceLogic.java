package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.LoginCredential;
import namoo.nara.castle.domain.entity.LoginIdType;
import namoo.nara.castle.domain.service.CastellanService;
import namoo.nara.castle.domain.service.data.CastellanUdo;
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
    public void createCastellan(String castleId, String name) {
        //
        Castellan castellan = Castellan.newInstance(castleId, name);
        this.castellanStore.create(castellan);
    }

    @Override
    public Castellan findCastellan(String castleId) {
        //
        return this.castellanStore.retrieve(castleId);
    }

    @Override
    public Castellan findCastellan(String loginId, LoginIdType loginIdType) {
        //
        return this.castellanStore.retrieveByLoginIdAndLoginIdType(loginId, loginIdType);
    }

    @Override
    public void modifyCastellan(String castleId, CastellanUdo castellanUdo) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        castellan.setName(castellanUdo.getName());
        this.castellanStore.update(castellan);
    }

    @Override
    public void modifyCastellanPhoto(String castleId, String photoId) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        castellan.setPhotoId(photoId);
        this.castellanStore.update(castellan);
    }

    @Override
    public void removeCastellan(String castleId) {
        //
        this.castellanStore.delete(castleId);
    }

    @Override
    public void addAccount(String castleId, String loginId, LoginIdType loginIdType) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        castellan.addAccount(loginId, loginIdType);
        this.castellanStore.update(castellan);
    }

    @Override
    public void removeAccount(String castleId, String loginId, LoginIdType loginIdType) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        castellan.removeAccount(loginId, loginIdType);
        this.castellanStore.update(castellan);
    }

    @Override
    public String findPassword(String castleId) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        LoginCredential credential = castellan.getCredential();
        if (credential == null) return null;
        return credential.getPassword();
    }

    @Override
    public void modifyPassword(String castleId, String password) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        castellan.setPasswordCredential(password);
        this.castellanStore.update(castellan);
    }

    @Override
    public void addEmail(String castleId, String email) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        castellan.addEmail(email);
        this.castellanStore.update(castellan);
    }

    @Override
    public void verifyEmail(String castleId, String email) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        castellan.verifyEmail(email);
        this.castellanStore.update(castellan);
    }

    @Override
    public void setPrimaryEmail(String castleId, String email) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        castellan.setPrimaryEmail(email);
        this.castellanStore.update(castellan);
    }

    @Override
    public void removeEmail(String castleId, String email) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        castellan.removeEmail(email);
        this.castellanStore.update(castellan);
    }

    @Override
    public void addJoinedMetro(String castleId, String metroId, String citizenId) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        castellan.addJoinedMetro(metroId, citizenId);
        this.castellanStore.update(castellan);
    }

    @Override
    public void removeJoinedMetro(String castleId, String metroId, String citizenId) {
        //
        Castellan castellan = this.castellanStore.retrieve(castleId);
        castellan.removeJoinedMetro(metroId, citizenId);
        this.castellanStore.update(castellan);
    }
}
