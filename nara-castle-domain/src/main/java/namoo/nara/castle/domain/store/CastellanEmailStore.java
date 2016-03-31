package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.CastellanEmail;

import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public interface CastellanEmailStore {

    void create(CastellanEmail castellanEmail, String castellanId);

    CastellanEmail retrieve(String email, String castellanId);

    List<CastellanEmail> retrieveCastellanEmails(String castellanId);

    void update(CastellanEmail castellanEmail, String castellanId);

    void delete(String email, String castellanId);

    boolean hasPrimaryEmail(String castellanId);

    CastellanEmail getPrimaryEmail(String castellanId);

    void updatePrimaryEmail(String email, String castellanId, boolean primaryEmail);

    String retrieveCastellanIdByVerifiedEmail(String email);

    int countCastellanEmail(String castellanId);

    boolean exist(String email, String castellanId);

    boolean existVerifiedEmail(String email);

    void deleteByCastellanId(String castellanId);
}
