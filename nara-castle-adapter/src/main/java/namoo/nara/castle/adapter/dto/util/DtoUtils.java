package namoo.nara.castle.adapter.dto.util;

import namoo.nara.castle.adapter.dto.*;
import namoo.nara.castle.domain.entity.*;
import namoo.nara.castle.domain.service.data.CastellanCdo;
import namoo.nara.castle.domain.service.data.CastleCdo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DtoUtils {
    //
    public static CastleCdo toCastleCdo(CastleBuildDto castleBuildDto) {
        //
        if (castleBuildDto == null) return null;
        CastleCdo castleCdo = new CastleCdo();
        castleCdo.setLocale(castleBuildDto.getLocale());
        return castleCdo;
    }

    public static CastellanCdo toCastellanCdo(CastellanCreationDto castellanCreationDto) {
        //
        if (castellanCreationDto == null) return null;
        CastellanCdo castellanCdo = new CastellanCdo();
        castellanCdo.setEmail(castellanCreationDto.getEmail());
        castellanCdo.setPassword(castellanCreationDto.getPassword());
        return castellanCdo;
    }

    public static CastleFindDto toCastleFindDto(Castle castle) {
        //
        if (castle == null) return null;
        CastleFindDto castleFindDto = new CastleFindDto();
        castleFindDto.setId(castle.getId());
        castleFindDto.setLocale(castle.getLocale());
        castleFindDto.setBuiltTime(castle.getBuiltTime().toInstant().toEpochMilli());
        return castleFindDto;
    }

    public static List<CastleFindDto> toCastleFindDto(List<Castle> castles) {
        //
        if (castles == null) return null;
        List<CastleFindDto> castleFindDtos = new ArrayList<>(castles.size());
        for (Castle castle : castles) {
            castleFindDtos.add(toCastleFindDto(castle));
        }
        return castleFindDtos;
    }

    public static CastellanFindDto toCastellanFindDto(Castellan castellan) {
        //
        if (castellan == null) return null;
        CastellanFindDto castellanFindDto = new CastellanFindDto();
        castellanFindDto.setId(castellan.getId());
        castellanFindDto.setAccounts(toAccountDto(castellan.getAccounts()));
        castellanFindDto.setCredential(toCredentialDto(castellan.getCredential()));
        castellanFindDto.setEmails(toEmailDto(castellan.getEmails()));
        castellanFindDto.setJoinedMetros(toJoinedMetroDto(castellan.getJoinedMetros()));
        castellanFindDto.setCreatedTime(castellan.getCreatedTime().toInstant().toEpochMilli());

        return castellanFindDto;
    }

    public static List<JoinedMetroDto> toJoinedMetroDto(List<JoinedMetro> joinedMetros) {
        //
        if (joinedMetros == null) return null;
        List<JoinedMetroDto> joinedMetroDtos = new ArrayList<>(joinedMetros.size());
        for (JoinedMetro joinedMetro : joinedMetros) {
            joinedMetroDtos.add(toJoinedMetroDto(joinedMetro));
        }
        return joinedMetroDtos;
    }

    public static JoinedMetroDto toJoinedMetroDto(JoinedMetro joinedMetro) {
        //
        if (joinedMetro == null) return null;
        JoinedMetroDto joinedMetroDto = new JoinedMetroDto();
        joinedMetroDto.setMetroId(joinedMetro.getMetroId());
        joinedMetroDto.setCitizenId(joinedMetro.getCitizenId());
        joinedMetroDto.setJoinedTime(joinedMetro.getJoinedTime().toInstant().toEpochMilli());
        return joinedMetroDto;
    }

    public static List<CastellanEmailDto> toEmailDto(Set<CastellanEmail> emails) {
        //
        if (emails == null) return null;
        List<CastellanEmailDto> emailDtos = new ArrayList<>(emails.size());
        for (CastellanEmail email : emails) {
            emailDtos.add(toEmailDto(email));
        }
        return emailDtos;
    }

    public static CastellanEmailDto toEmailDto(CastellanEmail email) {
        //
        if (email == null) return null;
        CastellanEmailDto emailDto = new CastellanEmailDto();
        emailDto.setAddress(email.getAddress());
        emailDto.setCreatedTime(email.getCreatedTime().toInstant().toEpochMilli());
        emailDto.setVerified(email.isVerified());
        if (email.getVerifiedTime() != null) {
            emailDto.setVerifiedTime(email.getVerifiedTime().toInstant().toEpochMilli());
        }
        emailDto.setPrimary(email.isPrimary());
        return emailDto;
    }

    public static LoginCredentialDto toCredentialDto(LoginCredential credential) {
        //
        if (credential == null) return null;
        LoginCredentialDto credentialDto = new LoginCredentialDto();
        credentialDto.setPassword(credential.getPassword());
        return credentialDto;
    }

    public static List<LoginAccountDto> toAccountDto(Set<LoginAccount> accounts) {
        //
        if (accounts == null) return null;
        List<LoginAccountDto> accountDtos = new ArrayList<>(accounts.size());
        for (LoginAccount account : accounts) {
            accountDtos.add(toAccountDto(account));
        }
        return accountDtos;
    }

    public static LoginAccountDto toAccountDto(LoginAccount account) {
        //
        if (account == null) return null;
        LoginAccountDto loginAccountDto = new LoginAccountDto();
        loginAccountDto.setLoginId(account.getLoginId());
        loginAccountDto.setLoginIdType(account.getLoginIdType().name());
        return loginAccountDto;
    }

}