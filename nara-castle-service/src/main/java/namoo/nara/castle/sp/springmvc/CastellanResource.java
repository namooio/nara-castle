package namoo.nara.castle.sp.springmvc;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.remote.CastellanRemote;
import namoo.nara.castle.remote.dto.CastellanCreateDto;
import namoo.nara.castle.remote.dto.CastellanReadDto;
import namoo.nara.castle.sp.util.CastleDtoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
@RestController
@RequestMapping("castellan")
public class CastellanResource implements CastellanRemote {

    private CastleService castleService;

    @Autowired
    public CastellanResource(CastleServiceLycler castleServiceLycler) {
        this.castleService = castleServiceLycler.requestCastleService();
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody CastellanCreateDto castellanCreateDto) {
        String id = castellanCreateDto.getId();
        String email = castellanCreateDto.getEmail();
        if (StringUtils.isEmpty(email)) {
            castleService.registerCastellan(id);
        }
        castleService.registerCastellan(id, email);
    }

    @Override
    @RequestMapping(value = "{castellanId}", method = RequestMethod.GET)
    public CastellanReadDto findCastellan(@PathVariable("castellanId") String castellanId) {
        Castellan castellan = castleService.findCastellan(castellanId);
        return CastleDtoUtil.toCastellanReadDto(castellan);
    }

    @Override
    @RequestMapping(value = "{castellanId}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("castellanId") String castellanId) {
        castleService.removeCastellan(castellanId);
    }

    @Override
    @RequestMapping(value = "email/{email:.+}", method = RequestMethod.GET)
    public CastellanReadDto findByVerifiedEmail(@PathVariable("email") String email) {
        Castellan castellan = castleService.findCastellanByVerifiedEmail(email);
        return CastleDtoUtil.toCastellanReadDto(castellan);
    }

    @Override
    @RequestMapping(value = "{castellanId}/email/{email:.+}", method = RequestMethod.POST)
    public void addEmail(@PathVariable("castellanId") String castellanId, @PathVariable("email") String email) {
        castleService.addCastellanEmail(email, castellanId);
    }

    @Override
    @RequestMapping(value = "{castellanId}/email/{email:.+}/verify", method = RequestMethod.PUT)
    public void verifyEmail(@PathVariable("castellanId") String castellanId, @PathVariable("email") String email) {
        castleService.verifyCastellanEmail(email, castellanId);
    }

    @Override
    @RequestMapping(value = "{castellanId}/email/{email:.+}/primary", method = RequestMethod.PUT)
    public void setEmailAsPrimary(@PathVariable("castellanId") String castellanId, @PathVariable("email") String email) {
        castleService.setAsPrimaryEmail(email, castellanId);
    }

    @Override
    @RequestMapping(value = "{castellanId}/email/{email:.+}", method = RequestMethod.DELETE)
    public void removeEmail(@PathVariable("castellanId") String castellanId, @PathVariable("email") String email) {
        castleService.removeCastellanEmail(email, castellanId);
    }

    @Override
    @RequestMapping(value = "{castellanId}/displayname", method = RequestMethod.GET)
    public String findCastellanDisplayName(@PathVariable("castellanId") String castellanId) {
        return castleService.findCastellanDisplayName(castellanId);
    }
}
