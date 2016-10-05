package namoo.nara.castle.domain.store.mapstore;

import namoo.nara.castle.domain.entity.account.AccountBook;
import namoo.nara.castle.domain.entity.account.LoginAccount;
import namoo.nara.castle.domain.entity.account.LoginIdType;
import namoo.nara.castle.domain.store.AccountBookStore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AccountBookMapStore implements AccountBookStore {
    //
    private Map<String, AccountBook> accountBookMap;

    public AccountBookMapStore() {
        //
        this.accountBookMap = new HashMap<>();
    }

    @Override
    public void create(AccountBook accountBook) {
        //
        this.accountBookMap.put(accountBook.getId(), accountBook);
    }

    @Override
    public AccountBook retrieve(String id) {
        //
        return this.accountBookMap.get(id);
    }

    @Override
    public AccountBook retrieveByLoginIdAndLoginIdType(String loginId, LoginIdType loginIdType) {
        //
        Collection<AccountBook> accountBooks = this.accountBookMap.values();
        for(AccountBook accountBook : accountBooks) {
            Set<LoginAccount> accounts = accountBook.getLoginAccounts();
            for(LoginAccount account : accounts) {
                if (loginId.equals(account.getLoginId()) && loginIdType.equals(account.getLoginIdType())) {
                    return accountBook;
                }
            }
        }
        return null;
    }

    @Override
    public void update(AccountBook accountBook) {
        //
        this.accountBookMap.put(accountBook.getId(), accountBook);
    }

    @Override
    public void delete(String id) {
        //
        this.accountBookMap.remove(id);
    }
}
