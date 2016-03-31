package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.CastellanEmail;

import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public interface CastellanEmailStore {

    void create(CastellanEmail castellanEmail, String castellanOid);

    CastellanEmail retrieve(String email, String castellanOid);

    List<CastellanEmail> retrieveCastellanEmails(String castellanOid);

    void update(CastellanEmail castellanEmail, String castellanOid);

    void delete(String email, String castellanOid);

    boolean hasPrimaryEmail(String castellanOid);

    CastellanEmail getPrimaryEmail(String castellanOid);

    void updatePrimaryEmail(String email, String castellanOid, boolean primaryEmail);

    String retrieveCastellanOidByVerifiedEmail(String email);

    int countCastellanEmail(String castellanOid);

    boolean exist(String email, String castellanOid);

    boolean existVerifiedEmail(String email);

    void deleteByCastellanOid(String castellanOid);
}
