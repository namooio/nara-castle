package namoo.nara.castle.domain.entity.account;

import namoo.nara.share.domain.ValueObject;

public class LoginCredential implements ValueObject {
    //
    private String password;

    public LoginCredential() {
        //
    }

    public LoginCredential(String password) {
        //
        this.password = password;
    }

    public static LoginCredential newInstance(String password) {
        //
        LoginCredential loginCredential = new LoginCredential(password);
        return loginCredential;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCredential{" +
                "password='" + password + '\'' +
                '}';
    }
}
