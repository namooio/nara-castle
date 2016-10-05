package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.LoginIdType;

import java.util.List;

public interface CastellanStore {
    //
    void create(Castellan castellan);
    Castellan retrieve(String id);
    Castellan retrieveByLoginIdAndLoginIdType(String loginId, LoginIdType loginIdType);
    List<Castellan> retrieveAll();
    void update(Castellan castellan);
    void delete(String id);
}
