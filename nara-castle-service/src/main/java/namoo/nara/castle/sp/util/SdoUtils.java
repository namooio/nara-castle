package namoo.nara.castle.sp.util;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.CastellanEmail;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.JoinedMetro;
import namoo.nara.castle.domain.service.data.CastleCdo;
import namoo.nara.castle.protocol.sdo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SdoUtils {
    //
    public static CastleCdo toCastleCdo(CastleBuildSdo castleBuildSdo) {
        //
        if (castleBuildSdo == null) return null;
        CastleCdo castleCdo = new CastleCdo();
        castleCdo.setCastellanEmail(castleBuildSdo.getCastellanEmail());
        castleCdo.setOriginMetroId(castleBuildSdo.getOriginMetroId());
        castleCdo.setOriginCitizenId(castleBuildSdo.getOriginCitizenId());
        castleCdo.setLocale(castleBuildSdo.getLocale());
        return castleCdo;
    }

    public static CastleSdo toCastleSdo(Castle castle) {
        //
        if (castle == null) return null;
        CastleSdo castleSdo = new CastleSdo();
        castleSdo.setId(castle.getId());
        castleSdo.setLocale(castle.getLocale());
        castleSdo.setBuiltTime(castle.getBuiltTime().toInstant().toEpochMilli());
        castleSdo.setCastellanSdo(toCastellanSdo(castle.getCastellan()));
        return castleSdo;
    }

    public static List<CastleSdo> toCastleSdo(List<Castle> castles) {
        //
        if (castles == null) return null;
        List<CastleSdo> castleSdos = new ArrayList<>(castles.size());
        for (Castle castle : castles) {
            castleSdos.add(toCastleSdo(castle));
        }
        return castleSdos;
    }

    public static CastellanSdo toCastellanSdo(Castellan castellan) {
        //
        if (castellan == null) return null;
        CastellanSdo castellanSdo = new CastellanSdo();
        castellanSdo.setEmails(toEmailSdo(castellan.getEmails()));
        castellanSdo.setJoinedMetros(toJoinedMetroSdo(castellan.getJoinedMetros()));

        return castellanSdo;
    }

    public static List<JoinedMetroSdo> toJoinedMetroSdo(List<JoinedMetro> joinedMetros) {
        //
        if (joinedMetros == null) return null;
        List<JoinedMetroSdo> joinedMetroSdos = new ArrayList<>(joinedMetros.size());
        for (JoinedMetro joinedMetro : joinedMetros) {
            joinedMetroSdos.add(toJoinedMetroSdo(joinedMetro));
        }
        return joinedMetroSdos;
    }

    public static JoinedMetroSdo toJoinedMetroSdo(JoinedMetro joinedMetro) {
        //
        if (joinedMetro == null) return null;
        JoinedMetroSdo joinedMetroSdo = new JoinedMetroSdo();
        joinedMetroSdo.setMetroId(joinedMetro.getMetroId());
        joinedMetroSdo.setCitizenId(joinedMetro.getCitizenId());
        joinedMetroSdo.setJoinedTime(joinedMetro.getJoinedTime().toInstant().toEpochMilli());
        return joinedMetroSdo;
    }

    public static List<CastellanEmailSdo> toEmailSdo(Set<CastellanEmail> emails) {
        //
        if (emails == null) return null;
        List<CastellanEmailSdo> emailDtos = new ArrayList<>(emails.size());
        for (CastellanEmail email : emails) {
            emailDtos.add(toEmailSdo(email));
        }
        return emailDtos;
    }

    public static CastellanEmailSdo toEmailSdo(CastellanEmail email) {
        //
        if (email == null) return null;
        CastellanEmailSdo emailDto = new CastellanEmailSdo();
        emailDto.setAddress(email.getAddress());
        emailDto.setCreatedTime(email.getCreatedTime().toInstant().toEpochMilli());
        return emailDto;
    }

}