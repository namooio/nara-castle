package namoo.nara.castle.sp.spring.web;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.JoinedMetro;
import namoo.nara.castle.domain.entity.LoginAccount;
import namoo.nara.castle.domain.entity.LoginIdType;
import namoo.nara.castle.domain.service.CastellanService;
import namoo.nara.castle.domain.service.data.CastellanCdo;
import namoo.nara.castle.protocol.CastellanProtocol;
import namoo.nara.castle.protocol.sdo.CastellanCreationSdo;
import namoo.nara.castle.protocol.sdo.CastellanFindSdo;
import namoo.nara.castle.protocol.sdo.JoinedMetroSdo;
import namoo.nara.castle.protocol.sdo.LoginAccountSdo;
import namoo.nara.castle.sp.util.SdoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("castle-api")
public class CastellanResource implements CastellanProtocol {
    //
    @Autowired
    private CastellanService castellanService;

    @Override
    @RequestMapping(value="castellans/{id}", method= RequestMethod.POST)
    public void createCastellan(
            @PathVariable("id") String castleId,
            @RequestBody CastellanCreationSdo castellanCreationSdo
    ) {
        //
        CastellanCdo castellanCdo = SdoUtils.toCastellanCdo(castellanCreationSdo);
        this.castellanService.createCastellan(castleId, castellanCdo);
    }

    @Override
    @RequestMapping(value="castellans/{id}", method= RequestMethod.GET)
    public CastellanFindSdo findCastellan(
            @PathVariable("id") String castleId
    ) {
        //
        Castellan castellan = this.castellanService.findCastellan(castleId);
        return SdoUtils.toCastellanFindSdo(castellan);
    }

    @Override
    @RequestMapping(value = "castellan", method= RequestMethod.GET)
    public CastellanFindSdo findCastellan(
            @RequestParam("loginId") String loginId,
            @RequestParam("loginIdType") String loginIdType
    ) {
        //
        Castellan castellan = this.castellanService.findCastellan(loginId, LoginIdType.valueOf(loginIdType));
        return SdoUtils.toCastellanFindSdo(castellan);
    }

    @Override
    @RequestMapping(value="castellans/{id}", method= RequestMethod.DELETE)
    public void removeCastellan(
            @PathVariable("id") String castleId
    ) {
        //
        this.castellanService.removeCastellan(castleId);
    }

    @Override
    @RequestMapping(value="castellans/{id}/account", method= RequestMethod.POST)
    public void addAccount(
            @PathVariable("id") String castleId,
            @RequestBody LoginAccountSdo accountSdo
    ) {
        //
        String loginId = accountSdo.getLoginId();
        String loginIdType = accountSdo.getLoginIdType();
        this.castellanService.addAccount(castleId, loginId, LoginIdType.valueOf(loginIdType));
    }

    @Override
    @RequestMapping(value = "castellans/{id}/accounts", method = RequestMethod.GET)
    public List<LoginAccountSdo> findAccounts(
            @PathVariable("id") String castleId
    ) {
        //
        Set<LoginAccount> accounts = this.castellanService.findAccounts(castleId);
        return SdoUtils.toAccountSdo(accounts);
    }

    @Override
    @RequestMapping(value="castellans/{id}/account", method= RequestMethod.DELETE)
    public void removeAccount(
            @PathVariable("id") String castleId,
            @RequestBody LoginAccountSdo accountDto
    ) {
        //
        String loginId = accountDto.getLoginId();
        String loginIdType = accountDto.getLoginIdType();
        this.castellanService.removeAccount(castleId, loginId, LoginIdType.valueOf(loginIdType));
    }

    @Override
    @RequestMapping(value="castellans/{id}/password", method= RequestMethod.GET)
    public String findPassword(
            @PathVariable("id") String castleId
    ) {
        //
        return castellanService.findPassword(castleId);
    }

    @Override
    @RequestMapping(value="castellans/{id}/password", method= RequestMethod.PUT)
    public void modifyPassword(
            @PathVariable("id") String castleId,
            @RequestBody String password
    ) {
        //
        this.castellanService.modifyPassword(castleId, password);
    }

    @Override
    @RequestMapping(value="castellans/{id}/email", method= RequestMethod.POST)
    public void addEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        //
        this.castellanService.addEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/email-verification", method= RequestMethod.PUT)
    public void verifyEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        //
        this.castellanService.verifyEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/primary-email", method= RequestMethod.PUT)
    public void setPrimaryEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        //
        this.castellanService.setPrimaryEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/email", method= RequestMethod.DELETE)
    public void removeEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        //
        this.castellanService.removeEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metro", method= RequestMethod.POST)
    public void addJoinedMetro(
            @PathVariable("id") String castleId,
            @RequestBody JoinedMetroSdo joinedMetroSdo
    ) {
        //
        String metroId = joinedMetroSdo.getMetroId();
        String citizenId = joinedMetroSdo.getCitizenId();
        this.castellanService.addJoinedMetro(castleId, metroId, citizenId);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metros", method= RequestMethod.GET)
    public List<JoinedMetroSdo> findJoinedMetros(
            @PathVariable("id") String castleId
    ) {
        //
        List<JoinedMetro> joinedMetros = castellanService.findJoinedMetros(castleId);
        return SdoUtils.toJoinedMetroSdo(joinedMetros);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metro", method= RequestMethod.DELETE)
    public void removeJoinedMetro(
            @PathVariable("id") String castleId,
            @RequestBody JoinedMetroSdo joinedMetroSdo
    ) {
        //
        String metroId = joinedMetroSdo.getMetroId();
        String citizenId = joinedMetroSdo.getCitizenId();
        this.castellanService.removeJoinedMetro(castleId, metroId, citizenId);
    }
}
