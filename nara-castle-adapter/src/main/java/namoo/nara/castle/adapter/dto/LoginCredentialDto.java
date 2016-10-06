package namoo.nara.castle.adapter.dto;

import java.io.Serializable;

public class LoginCredentialDto implements Serializable {
    //
    private String password;

    public LoginCredentialDto() {
        //
    }

    public LoginCredentialDto(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
