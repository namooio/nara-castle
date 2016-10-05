package namoo.nara.castle.domain.entity.account;

import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class AccountBook extends Entity implements Aggregate {
    //
    private Set<LoginAccount> loginAccounts;
    private LoginCredential credential;

    public AccountBook(String id) {
        super(id);
    }

    public static AccountBook newInstance(String id) {
        //
        AccountBook accountBook = new AccountBook(id);
        accountBook.setLoginAccounts(new HashSet<>());
        return accountBook;
    }

    public void addAccount(String loginId, LoginIdType loginIdType) {
        //
        LoginAccount loginAccount = new LoginAccount();
        loginAccount.setLoginId(loginId);
        loginAccount.setLoginIdType(loginIdType);
        loginAccount.setCreatedTime(ZonedDateTime.now());
        this.loginAccounts.add(loginAccount);
    }

    public void removeAccount(String loginId, LoginIdType loginIdType) {
        //
        LoginAccount loginAccount = findAccount(loginId, loginIdType);
        if (loginAccount != null) {
            this.loginAccounts.remove(loginAccount);
        }
    }

    public LoginAccount findAccount(String loginId, LoginIdType loginIdType) {
        //
        for(LoginAccount loginAccount : this.loginAccounts) {
            if (loginId.equals(loginAccount.getLoginId()) && loginIdType.equals(loginAccount.getLoginIdType())) {
                return loginAccount;
            }
        }
        return null;
    }

    public boolean hasAccount(LoginAccount newAccount) {
        //
        for(LoginAccount account : loginAccounts) {
            if (newAccount.getLoginId().equals(account.getLoginId())
                    && newAccount.getLoginIdType().equals(account.getLoginIdType())) {
                return true;
            }
        }
        return false;
    }

    public void setPasswordCredential(String password) {
        //
        this.credential = new LoginCredential(password);
    }

    public Set<LoginAccount> getLoginAccounts() {
        return loginAccounts;
    }

    public void setLoginAccounts(Set<LoginAccount> loginAccounts) {
        this.loginAccounts = loginAccounts;
    }

    public LoginCredential getCredential() {
        return credential;
    }

    public void setCredential(LoginCredential credential) {
        this.credential = credential;
    }

    @Override
    public String toString() {
        return "AccountBook{" +
                "loginAccounts=" + loginAccounts +
                ", credential=" + credential +
                '}';
    }

    public static AccountBook getSample() {
        //
        AccountBook accountBook = AccountBook.newInstance("0");
        accountBook.addAccount("kchuh", LoginIdType.Username);
        accountBook.addAccount("kchuh@nextree.co.kr", LoginIdType.Email);
        accountBook.addAccount("michael7557@gmail.com", LoginIdType.Email);
        accountBook.addAccount("99999999", LoginIdType.OAuth_Google);
        accountBook.setPasswordCredential("1234");

        return accountBook;
    }

    public static void main(String[] args) {
        //
        AccountBook sample = AccountBook.getSample();
        System.out.println(sample);
    }
}
