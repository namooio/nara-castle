package namoo.nara.castle.adapter.logic;

import namoo.nara.castle.adapter.CastellanAdapter;
import namoo.nara.castle.adapter.dto.*;
import namoo.nara.castle.adapter.dto.util.DtoUtils;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.LoginIdType;
import namoo.nara.castle.domain.service.CastellanService;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.domain.service.data.CastellanUdo;

public class CastellanAdatperLogic implements CastellanAdapter {
    //
    private CastellanService castellanService;

    public CastellanAdatperLogic(CastleServiceLycler serviceLycler) {
        //
        this.castellanService = serviceLycler.requestCastellanService();
    }

    @Override
    public void createCastellan(String castleId, CastellanCreationDto castellanCreationDto) {
        //
        String castellanName = castellanCreationDto.getName();
        this.castellanService.createCastellan(castleId, castellanName);
    }

    @Override
    public CastellanFindDto findCastellan(String castleId) {
        //
        Castellan castellan = this.castellanService.findCastellan(castleId);
        return DtoUtils.toCastellanFindDto(castellan);
    }

    @Override
    public CastellanFindDto findCastellan(String loginId, String loginIdType) {
        //
        Castellan castellan = this.castellanService.findCastellan(loginId, LoginIdType.valueOf(loginIdType));
        return DtoUtils.toCastellanFindDto(castellan);
    }

    @Override
    public void modifyCastellan(String castleId, CastellanModificationDto castellanModificationDto) {
        //
        CastellanUdo castellanUdo = DtoUtils.toCastellanUdo(castellanModificationDto);
        this.castellanService.modifyCastellan(castleId, castellanUdo);
    }

    @Override
    public void modifyCastellanPhoto(String castleId, String photoId) {
        //
        this.castellanService.modifyCastellanPhoto(castleId, photoId);
    }

    @Override
    public void removeCastellan(String castleId) {
        //
        this.castellanService.removeCastellan(castleId);
    }

    @Override
    public void addAccount(String castleId, LoginAccountDto accountDto) {
        //
        String loginId = accountDto.getLoginId();
        String loginIdType = accountDto.getLoginIdType();
        this.castellanService.addAccount(castleId, loginId, LoginIdType.valueOf(loginIdType));
    }

    @Override
    public void removeAccount(String castleId, LoginAccountDto accountDto) {
        //
        String loginId = accountDto.getLoginId();
        String loginIdType = accountDto.getLoginIdType();
        this.castellanService.removeAccount(castleId, loginId, LoginIdType.valueOf(loginIdType));
    }

    @Override
    public void modifyPasswordCredential(String castleId, String password) {
        //
        this.castellanService.modifyPasswordCredential(castleId, password);
    }

    @Override
    public void addEmail(String castleId, String email) {
        //
        this.castellanService.addEmail(castleId, email);
    }

    @Override
    public void verifyEmail(String castleId, String email) {
        //
        this.castellanService.verifyEmail(castleId, email);
    }

    @Override
    public void setPrimaryEmail(String castleId, String email) {
        //
        this.castellanService.setPrimaryEmail(castleId, email);
    }

    @Override
    public void removeEmail(String castleId, String email) {
        //
        this.castellanService.removeEmail(castleId, email);
    }

    @Override
    public void addJoinedMetro(String castleId, JoinedMetroDto joinedMetroDto) {
        //
        String metroId = joinedMetroDto.getMetroId();
        String citizenId = joinedMetroDto.getCitizenId();
        this.castellanService.addJoinedMetro(castleId, metroId, citizenId);
    }

    @Override
    public void removeJoinedMetro(String castleId, JoinedMetroDto joinedMetroDto) {
        //
        String metroId = joinedMetroDto.getMetroId();
        String citizenId = joinedMetroDto.getCitizenId();
        this.castellanService.removeJoinedMetro(castleId, metroId, citizenId);
    }
}
