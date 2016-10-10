package namoo.nara.castle.sp.spring.web;

import namoo.nara.castle.adapter.dto.CastellanCreationDto;
import namoo.nara.castle.adapter.dto.CastellanFindDto;
import namoo.nara.castle.adapter.dto.JoinedMetroDto;
import namoo.nara.castle.adapter.dto.LoginAccountDto;
import namoo.nara.castle.adapter.logic.CastellanAdatperLogic;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("castle-api")
public class CastellanResource extends CastellanAdatperLogic {
    //
    @Autowired
    public CastellanResource(CastleServiceLycler serviceLycler) {
        super(serviceLycler);
    }

    @Override
    @RequestMapping(value="castellans/{id}", method= RequestMethod.POST)
    public void createCastellan(
            @PathVariable("id") String castleId,
            @RequestBody CastellanCreationDto castellanCreationDto
    ) {
        super.createCastellan(castleId, castellanCreationDto);
    }

    @Override
    @RequestMapping(value="castellans/{id}", method= RequestMethod.GET)
    public CastellanFindDto findCastellan(
            @PathVariable("id") String castleId
    ) {
        return super.findCastellan(castleId);
    }

    @Override
    @RequestMapping(value = "castellan", method= RequestMethod.GET)
    public CastellanFindDto findCastellan(
            @RequestParam("loginId") String loginId,
            @RequestParam("loginIdType") String loginIdType
    ) {
        return super.findCastellan(loginId, loginIdType);
    }

    @Override
    @RequestMapping(value="castellans/{id}", method= RequestMethod.DELETE)
    public void removeCastellan(
            @PathVariable("id") String castleId
    ) {
        super.removeCastellan(castleId);
    }

    @Override
    @RequestMapping(value="castellans/{id}/account", method= RequestMethod.POST)
    public void addAccount(
            @PathVariable("id") String castleId,
            @RequestBody LoginAccountDto accountDto
    ) {
        super.addAccount(castleId, accountDto);
    }

    @Override
    @RequestMapping(value = "castellans/{id}/accounts", method = RequestMethod.GET)
    public List<LoginAccountDto> findAccounts(
            @PathVariable("id") String castleId
    ) {
        return super.findAccounts(castleId);
    }

    @Override
    @RequestMapping(value="castellans/{id}/account", method= RequestMethod.DELETE)
    public void removeAccount(
            @PathVariable("id") String castleId,
            @RequestBody LoginAccountDto accountDto
    ) {
        super.removeAccount(castleId, accountDto);
    }

    @Override
    @RequestMapping(value="castellans/{id}/password", method= RequestMethod.GET)
    public String findPassword(
            @PathVariable("id") String castleId
    ) {
        return super.findPassword(castleId);
    }

    @Override
    @RequestMapping(value="castellans/{id}/password", method= RequestMethod.PUT)
    public void modifyPassword(
            @PathVariable("id") String castleId,
            @RequestBody String password
    ) {
        super.modifyPassword(castleId, password);
    }

    @Override
    @RequestMapping(value="castellans/{id}/email", method= RequestMethod.POST)
    public void addEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        super.addEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/email-verification", method= RequestMethod.PUT)
    public void verifyEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        super.verifyEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/primary-email", method= RequestMethod.PUT)
    public void setPrimaryEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        super.setPrimaryEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/email", method= RequestMethod.DELETE)
    public void removeEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        super.removeEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metro", method= RequestMethod.POST)
    public void addJoinedMetro(
            @PathVariable("id") String castleId,
            @RequestBody JoinedMetroDto joinedMetroDto
    ) {
        super.addJoinedMetro(castleId, joinedMetroDto);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metros", method= RequestMethod.GET)
    public List<JoinedMetroDto> findJoinedMetros(
            @PathVariable("id") String castleId
    ) {
        return super.findJoinedMetros(castleId);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metro", method= RequestMethod.DELETE)
    public void removeJoinedMetro(
            @PathVariable("id") String castleId,
            @RequestBody JoinedMetroDto joinedMetroDto
    ) {
        super.removeJoinedMetro(castleId, joinedMetroDto);
    }
}
