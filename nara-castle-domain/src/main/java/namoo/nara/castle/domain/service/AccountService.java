package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.account.AccountBook;
import namoo.nara.castle.domain.entity.account.LoginIdType;

public interface AccountService {
    //
    void createAccountBook(String id);
    AccountBook findAccountBook(String id);
    AccountBook findAccountBook(String loginId, LoginIdType loginIdType);
    void removeAccountBook(String id);

    void addAccount(String id, String loginId, LoginIdType loginIdType);
    void removeAccount(String id, String loginId, LoginIdType loginIdType);
    void modifyPasswordCredential(String id, String password);
}
