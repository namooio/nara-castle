package namoo.nara.castle.sp.util;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.remote.dto.CastellanReadDto;

/**
 * Created by kchuh@nextree.co.kr on 2016. 3. 15..
 */
public class CastleDtoUtil {

    public static CastellanReadDto toCastellanReadDto(Castellan castellan) {
        if (castellan == null) return null;
        CastellanReadDto castellanReadDto = new CastellanReadDto();
        castellanReadDto.setId(castellan.getOid());
        castellanReadDto.setDisplayName(castellan.getDisplayName());
        return castellanReadDto;
    }
}
