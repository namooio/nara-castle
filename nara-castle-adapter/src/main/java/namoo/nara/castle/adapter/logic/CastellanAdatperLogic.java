package namoo.nara.castle.adapter.logic;

import namoo.nara.castle.adapter.CastellanAdapter;
import namoo.nara.castle.adapter.dto.*;
import namoo.nara.castle.domain.service.CastellanService;
import namoo.nara.castle.domain.service.CastleServiceLycler;

public class CastellanAdatperLogic implements CastellanAdapter {
    //
    private CastellanService castellanService;

    public CastellanAdatperLogic(CastleServiceLycler serviceLycler) {
        //
        this.castellanService = serviceLycler.requestCastellanService();
    }

    @Override
    public void createCastellan(String castleId, CastellanCreationDto castellanCreationDto) {

    }

    @Override
    public CastellanFindDto findCastellan(String castleId) {
        return null;
    }

    @Override
    public CastellanFindDto findCastellan(String loginId, String loginIdType) {
        return null;
    }

    @Override
    public void modifyCastellan(String castleId, CastellanModificationDto castellanModificationDto) {

    }

    @Override
    public void modifyCastellanPhoto(String castleId, String photoId) {

    }

    @Override
    public void removeCastellan(String castleId) {

    }

    @Override
    public void addAccount(String castleId, LoginAccountDto accountDto) {

    }

    @Override
    public void removeAccount(String castleId, LoginAccountDto accountDto) {

    }

    @Override
    public void modifyPasswordCredential(String castleId, String password) {

    }

    @Override
    public void addEmail(String castleId, String email) {

    }

    @Override
    public void verifyEmail(String castleId, String email) {

    }

    @Override
    public void setPrimaryEmail(String castleId, String email) {

    }

    @Override
    public void removeEmail(String castleId, String email) {

    }

    @Override
    public void addJoinedMetro(String castleId, JoinedMetroDto joinedMetroDto) {

    }

    @Override
    public void removeJoinedMetro(String castleId, JoinedMetroDto joinedMetroDto) {

    }
}
