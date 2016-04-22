package namoo.nara.castle.da.mongo.document.history;

import namoo.nara.castle.domain.entity.history.AccountBook;
import namoo.nara.castle.domain.entity.history.LoginAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class AccountBookDoc {

    private List<LoginAccountDoc> accountList;

    public AccountBookDoc() {
        //
    }

    public static AccountBookDoc newInstance(AccountBook accountBook) {
        //
        AccountBookDoc accountBookDoc = new AccountBookDoc();

        List<LoginAccount> loginAccounts = accountBook.findAll();
        if (loginAccounts != null) {
            for(LoginAccount loginAccount : loginAccounts) {
                accountBookDoc.addLoginAccount(LoginAccountDoc.newInstance(loginAccount));
            }
        }

        return accountBookDoc;
    }

    public AccountBook toDomain() {
        //
        AccountBook accountBook = new AccountBook();

        if (accountList != null) {
            for(LoginAccountDoc loginAccountDoc : accountList) {
                accountBook.addAccount(loginAccountDoc.toDomain());
            }
        }
        return accountBook;
    }

    public void addLoginAccount(LoginAccountDoc loginAccount) {
        //
        if (accountList == null) accountList = new ArrayList<>();
        accountList.add(loginAccount);
    }

    public List<LoginAccountDoc> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<LoginAccountDoc> accountList) {
        this.accountList = accountList;
    }
}
