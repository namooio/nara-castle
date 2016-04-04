package namoo.nara.castle.domain.entity.history;

import java.util.ArrayList;
import java.util.List;

public class AccountBook {
    //
    private List<LoginAccount> accountList = new ArrayList<>();

    public AccountBook() {
        //
    }

    public void addAccount(LoginAccount account) {
        //
        accountList.add(account);
    }

    public void removeAccount(LoginAccount account) {
        //
        accountList.remove(account);
    }

    public boolean existAccount(String loginUserId) {
        //
        if (findAccount(loginUserId) != null) {
            return true;
        }

        return false;
    }

    public LoginAccount findAccount(String loginUserId) {
        //
        for(LoginAccount account : accountList) {
            if (account.getLoginUserId().equals(loginUserId)) {
                return account;
            }
        }

        return null;
    }

    public List<LoginAccount> findAll() {
        //
        return accountList;
    }
}