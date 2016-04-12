package namoo.nara.castle.da.mongo.mdo.history;

import namoo.nara.castle.domain.entity.history.AccountBook;
import namoo.nara.castle.domain.entity.history.LoginAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class AccountBookMdo {

    private List<LoginAccountMdo> accountMdoList;

    public AccountBookMdo() {
        //
    }

    public static AccountBookMdo newInstance(AccountBook accountBook) {
        //
        AccountBookMdo accountBookMdo = new AccountBookMdo();

        List<LoginAccount> loginAccounts = accountBook.findAll();
        if (loginAccounts != null) {
            for(LoginAccount loginAccount : loginAccounts) {
                accountBookMdo.addLoginAccountMdo(LoginAccountMdo.newInstance(loginAccount));
            }
        }

        return accountBookMdo;
    }

    public AccountBook toDomain() {
        //
        AccountBook accountBook = new AccountBook();

        if (accountMdoList != null) {
            for(LoginAccountMdo loginAccountMdo : accountMdoList) {
                accountBook.addAccount(loginAccountMdo.toDomain());
            }
        }
        return accountBook;
    }

    public void addLoginAccountMdo(LoginAccountMdo loginAccountMdo) {
        //
        if (accountMdoList == null) accountMdoList = new ArrayList<>();
        accountMdoList.add(loginAccountMdo);
    }

    public List<LoginAccountMdo> getAccountMdoList() {
        return accountMdoList;
    }

    public void setAccountMdoList(List<LoginAccountMdo> accountMdoList) {
        this.accountMdoList = accountMdoList;
    }
}
