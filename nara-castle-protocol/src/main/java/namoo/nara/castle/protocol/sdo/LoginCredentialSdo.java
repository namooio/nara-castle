package namoo.nara.castle.protocol.sdo;

import java.io.Serializable;

public class LoginCredentialSdo implements Serializable {
    //
    private String password;

    public LoginCredentialSdo() {
        //
    }

    public LoginCredentialSdo(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
