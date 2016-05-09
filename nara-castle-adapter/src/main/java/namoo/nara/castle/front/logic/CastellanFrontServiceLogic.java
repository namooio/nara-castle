package namoo.nara.castle.front.logic;

import namoo.nara.castle.front.dto.CastellanFindDto;
import namoo.nara.castle.front.dto.util.DomainConversionUtil;
import namoo.nara.castle.front.CastellanFrontService;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.service.CastellanService;
import namoo.nara.castle.domain.service.CastleServiceLycler;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastellanFrontServiceLogic implements CastellanFrontService {
    //
    private CastellanService castellanService;

    public CastellanFrontServiceLogic(CastleServiceLycler castleServiceLycler) {
        //
        castellanService = castleServiceLycler.requestCastellanService();
    }

    @Override
    public CastellanFindDto findCastellan(String id) {
        //
        Castellan castellan = castellanService.findCastellan(id);
        return DomainConversionUtil.toCastellanFindDto(castellan);
    }

    @Override
    public void modifyDisplayName(String id, String displayName) {
        //
        castellanService.modifyDisplayName(id, displayName);
    }

    @Override
    public void modifyPhoto(String id, String photoId) {
        //
        castellanService.modifyPhoto(id, photoId);
    }

    @Override
    public void modifyPrimaryEmail(String id, String email) {
        //
        castellanService.modifyPrimaryEmail(id, email);
    }

    @Override
    public void modifyPrimaryPhone(String id, String phoneNumber) {
        //
        castellanService.modifyPrimaryPhone(id, phoneNumber);
    }
}
