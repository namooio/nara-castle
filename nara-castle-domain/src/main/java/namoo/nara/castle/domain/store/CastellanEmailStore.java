package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.contact.UserEmail;

import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public interface CastellanEmailStore {

    void create(UserEmail castellanEmail, String castellanId);

    UserEmail retrieve(String email, String castellanId);

    List<UserEmail> retrieveCastellanEmails(String castellanId);

    void update(UserEmail castellanEmail, String castellanId);

    void delete(String email, String castellanId);

    boolean hasPrimaryEmail(String castellanId);

    UserEmail getPrimaryEmail(String castellanId);

    void updatePrimaryEmail(String email, String castellanId, boolean primaryEmail);

    String retrieveCastellanIdByVerifiedEmail(String email);

    int countCastellanEmail(String castellanId);

    boolean exist(String email, String castellanId);

    boolean existVerifiedEmail(String email);

    void deleteByCastellanId(String castellanId);
}
