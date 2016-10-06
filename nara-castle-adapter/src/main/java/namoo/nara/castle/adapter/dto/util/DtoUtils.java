package namoo.nara.castle.adapter.dto.util;

import namoo.nara.castle.adapter.dto.CastellanFindDto;
import namoo.nara.castle.adapter.dto.CastellanModificationDto;
import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.service.data.CastellanUdo;
import namoo.nara.castle.domain.service.data.CastleCdo;

import java.util.ArrayList;
import java.util.List;

public class DtoUtils {
    //
    public static final String LOGIN_ID_TYPE_USERNAME = "Username";
    public static final String LOGIN_ID_TYPE_EMAIL = "Email";
    public static final String LOGIN_ID_TYPE_FACEBOOK = "Facebook";
    public static final String LOGIN_ID_TYPE_GOOGLE = "Google";
    public static final String LOGIN_ID_TYPE_GITHUB = "GitHub";

    public static CastleCdo toCastleCdo(CastleBuildDto castleBuildDto) {
        //
        if (castleBuildDto == null) return null;
        CastleCdo castleCdo = new CastleCdo();
        castleCdo.setLocale(castleBuildDto.getLocale());
        castleCdo.setCastellanName(castleBuildDto.getCastellanName());
        castleCdo.setCastellanEmail(castleBuildDto.getCastellanEmail());
        return castleCdo;
    }

    public static CastleFindDto toCastleFindDto(Castle castle) {
        //
        if (castle == null) return null;
        CastleFindDto castleFindDto = new CastleFindDto();
        castleFindDto.setLocale(castle.getLocale());
        castleFindDto.setBuiltTime(castle.getBuiltTime());
        return castleFindDto;
    }

    public static List<CastleFindDto> toCastleFindDto(List<Castle> castles) {
        //
        if (castles == null) return null;
        List<CastleFindDto> castleFindDtos = new ArrayList<>(castles.size());
        for(Castle castle : castles) {
            castleFindDtos.add(toCastleFindDto(castle));
        }
        return castleFindDtos;
    }

    public static CastellanFindDto toCastellanFindDto(Castellan castellan) {
        //
        if (castellan == null) return null;

        return null;
    }

    public static CastellanUdo toCastellanUdo(CastellanModificationDto castellanModificationDto) {
        //
        if (castellanModificationDto == null) return null;
        CastellanUdo castellanUdo = new CastellanUdo();
        castellanUdo.setName(castellanModificationDto.getName());
        return castellanUdo;
    }
}
