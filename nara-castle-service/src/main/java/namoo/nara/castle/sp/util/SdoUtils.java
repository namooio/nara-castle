package namoo.nara.castle.sp.util;

import namoo.nara.castle.protocol.sdo.*;
import namoo.nara.castle.domain.entity.*;
import namoo.nara.castle.domain.service.data.CastellanCdo;
import namoo.nara.castle.domain.service.data.CastleCdo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SdoUtils {
    //
    public static CastleCdo toCastleCdo(CastleBuildSdo castleBuildSdo) {
        //
        if (castleBuildSdo == null) return null;
        CastleCdo castleCdo = new CastleCdo();
        castleCdo.setLocale(castleBuildSdo.getLocale());
        return castleCdo;
    }

    public static CastellanCdo toCastellanCdo(CastellanCreationSdo castellanCreationSdo) {
        //
        if (castellanCreationSdo == null) return null;
        CastellanCdo castellanCdo = new CastellanCdo();
        castellanCdo.setEmail(castellanCreationSdo.getEmail());
        castellanCdo.setPassword(castellanCreationSdo.getPassword());
        return castellanCdo;
    }

    public static CastleFindSdo toCastleFindSdo(Castle castle) {
        //
        if (castle == null) return null;
        CastleFindSdo castleFindSdo = new CastleFindSdo();
        castleFindSdo.setId(castle.getId());
        castleFindSdo.setLocale(castle.getLocale());
        castleFindSdo.setBuiltTime(castle.getBuiltTime().toInstant().toEpochMilli());
        return castleFindSdo;
    }

    public static List<CastleFindSdo> toCastleFindSdo(List<Castle> castles) {
        //
        if (castles == null) return null;
        List<CastleFindSdo> castleFindSdos = new ArrayList<>(castles.size());
        for (Castle castle : castles) {
            castleFindSdos.add(toCastleFindSdo(castle));
        }
        return castleFindSdos;
    }

    public static CastellanFindSdo toCastellanFindSdo(Castellan castellan) {
        //
        if (castellan == null) return null;
        CastellanFindSdo castellanFindSdo = new CastellanFindSdo();
        castellanFindSdo.setId(castellan.getId());
        castellanFindSdo.setAccounts(toAccountSdo(castellan.getAccounts()));
        castellanFindSdo.setCredential(toCredentialSdo(castellan.getCredential()));
        castellanFindSdo.setEmails(toEmailSdo(castellan.getEmails()));
        castellanFindSdo.setJoinedMetros(toJoinedMetroSdo(castellan.getJoinedMetros()));
        castellanFindSdo.setCreatedTime(castellan.getCreatedTime().toInstant().toEpochMilli());

        return castellanFindSdo;
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
        emailDto.setVerified(email.isVerified());
        if (email.getVerifiedTime() != null) {
            emailDto.setVerifiedTime(email.getVerifiedTime().toInstant().toEpochMilli());
        }
        emailDto.setPrimary(email.isPrimary());
        return emailDto;
    }

    public static LoginCredentialSdo toCredentialSdo(LoginCredential credential) {
        //
        if (credential == null) return null;
        LoginCredentialSdo credentialDto = new LoginCredentialSdo();
        credentialDto.setPassword(credential.getPassword());
        return credentialDto;
    }

    public static List<LoginAccountSdo> toAccountSdo(Set<LoginAccount> accounts) {
        //
        if (accounts == null) return null;
        List<LoginAccountSdo> accountDtos = new ArrayList<>(accounts.size());
        for (LoginAccount account : accounts) {
            accountDtos.add(toAccountSdo(account));
        }
        return accountDtos;
    }

    public static LoginAccountSdo toAccountSdo(LoginAccount account) {
        //
        if (account == null) return null;
        LoginAccountSdo loginAccountSdo = new LoginAccountSdo();
        loginAccountSdo.setLoginId(account.getLoginId());
        loginAccountSdo.setLoginIdType(account.getLoginIdType().name());
        return loginAccountSdo;
    }

}