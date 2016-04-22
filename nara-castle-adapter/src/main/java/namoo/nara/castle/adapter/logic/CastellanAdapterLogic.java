package namoo.nara.castle.adapter.logic;

import namoo.nara.castle.adapter.dto.CastellanFindDto;
import namoo.nara.castle.adapter.dto.util.DomainConversionUtil;
import namoo.nara.castle.adapter.service.CastellanAdapter;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.service.CastellanService;
import namoo.nara.castle.domain.service.CastleServiceLycler;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastellanAdapterLogic implements CastellanAdapter {
    //
    private CastellanService castellanService;

    public CastellanAdapterLogic(CastleServiceLycler castleServiceLycler) {
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
