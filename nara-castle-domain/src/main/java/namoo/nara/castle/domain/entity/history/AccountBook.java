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

    public void clear() {
        accountList.clear();
    }

    public void remove(LoginAccount account) {
        //
        accountList.remove(account);
    }

    public boolean exist(String loginUserId) {
        //
        if (find(loginUserId) != null) {
            return true;
        }

        return false;
    }

    public LoginAccount find(String loginUserId) {
        //
        for(LoginAccount account : accountList) {
            if (account.getLoginId().equals(loginUserId)) {
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