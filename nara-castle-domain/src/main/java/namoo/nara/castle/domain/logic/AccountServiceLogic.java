package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.account.AccountBook;
import namoo.nara.castle.domain.entity.account.LoginIdType;
import namoo.nara.castle.domain.service.AccountService;
import namoo.nara.castle.domain.store.AccountBookStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;

public class AccountServiceLogic implements AccountService {
    //
    private AccountBookStore accountBookStore;

    public AccountServiceLogic(CastleStoreLycler storeLycler) {
        //
        this.accountBookStore = storeLycler.requestAccountBookStore();
    }

    @Override
    public void createAccountBook(String id) {
        //
        AccountBook accountBook = AccountBook.newInstance(id);
        this.accountBookStore.create(accountBook);
    }

    @Override
    public AccountBook findAccountBook(String id) {
        //
        return this.accountBookStore.retrieve(id);
    }

    @Override
    public AccountBook findAccountBook(String loginId, LoginIdType loginIdType) {
        //
        AccountBook accountBook = this.accountBookStore.retrieveByLoginIdAndLoginIdType(loginId, loginIdType);
        return accountBook;
    }

    @Override
    public void removeAccountBook(String id) {
        //
        this.accountBookStore.delete(id);
    }

    @Override
    public void addAccount(String id, String loginId, LoginIdType loginIdType) {
        //
        AccountBook accountBook = this.accountBookStore.retrieve(id);
        accountBook.addAccount(loginId, loginIdType);
        this.accountBookStore.update(accountBook);
    }

    @Override
    public void removeAccount(String id, String loginId, LoginIdType loginIdType) {
        //
        AccountBook accountBook = this.accountBookStore.retrieve(id);
        accountBook.removeAccount(loginId, loginIdType);
        this.accountBookStore.update(accountBook);
    }

    @Override
    public void modifyPasswordCredential(String id, String password) {
        //
        AccountBook accountBook = this.accountBookStore.retrieve(id);
        accountBook.setPasswordCredential(password);
        this.accountBookStore.update(accountBook);
    }
}
