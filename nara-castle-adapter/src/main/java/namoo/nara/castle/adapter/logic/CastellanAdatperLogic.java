package namoo.nara.castle.adapter.logic;

import namoo.nara.castle.adapter.CastellanAdapter;
import namoo.nara.castle.adapter.dto.CastellanCreationDto;
import namoo.nara.castle.adapter.dto.CastellanFindDto;
import namoo.nara.castle.adapter.dto.JoinedMetroDto;
import namoo.nara.castle.adapter.dto.LoginAccountDto;
import namoo.nara.castle.adapter.dto.util.DtoUtils;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.JoinedMetro;
import namoo.nara.castle.domain.entity.LoginAccount;
import namoo.nara.castle.domain.entity.LoginIdType;
import namoo.nara.castle.domain.service.CastellanService;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.domain.service.data.CastellanCdo;

import java.util.List;
import java.util.Set;

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
        CastellanCdo castellanCdo = DtoUtils.toCastellanCdo(castellanCreationDto);
        this.castellanService.createCastellan(castleId, castellanCdo);
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
    public List<LoginAccountDto> findAccounts(String castleId) {
        //
        Set<LoginAccount> accounts = this.castellanService.findAccounts(castleId);
        return DtoUtils.toAccountDto(accounts);
    }

    @Override
    public void removeAccount(String castleId, LoginAccountDto accountDto) {
        //
        String loginId = accountDto.getLoginId();
        String loginIdType = accountDto.getLoginIdType();
        this.castellanService.removeAccount(castleId, loginId, LoginIdType.valueOf(loginIdType));
    }

    @Override
    public String findPassword(String castleId) {
        //
        return castellanService.findPassword(castleId);
    }

    @Override
    public void modifyPassword(String castleId, String password) {
        //
        this.castellanService.modifyPassword(castleId, password);
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
    public List<JoinedMetroDto> findJoinedMetros(String castleId) {
        //
        List<JoinedMetro> joinedMetros = castellanService.findJoinedMetros(castleId);
        return DtoUtils.toJoinedMetroDto(joinedMetros);
    }

    @Override
    public void removeJoinedMetro(String castleId, JoinedMetroDto joinedMetroDto) {
        //
        String metroId = joinedMetroDto.getMetroId();
        String citizenId = joinedMetroDto.getCitizenId();
        this.castellanService.removeJoinedMetro(castleId, metroId, citizenId);
    }
}
