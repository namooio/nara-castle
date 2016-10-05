package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.account.AccountBook;
import namoo.nara.castle.domain.entity.account.LoginIdType;

public interface AccountBookStore {
    //
    void create(AccountBook accountBook);
    AccountBook retrieve(String id);
    AccountBook retrieveByLoginIdAndLoginIdType(String loginId, LoginIdType loginIdType);
    void update(AccountBook accountBook);

    void delete(String id);
}
