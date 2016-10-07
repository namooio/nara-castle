package namoo.nara.castle.adapter.dto;

import java.io.Serializable;

public class CastellanCreationDto implements Serializable {
    //
    private String email;
    private String password;

    public CastellanCreationDto() {
        //
    }

    public CastellanCreationDto(String email, String password) {
        //
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
