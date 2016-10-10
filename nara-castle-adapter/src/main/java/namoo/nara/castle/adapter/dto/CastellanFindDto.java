package namoo.nara.castle.adapter.dto;

import java.io.Serializable;
import java.util.List;

public class CastellanFindDto implements Serializable {
    //
    private String id;

    private List<LoginAccountDto> accounts;
    private LoginCredentialDto credential;

    private List<CastellanEmailDto> emails;

    private List<JoinedMetroDto> joinedMetros;

    private long createdTime;

    public CastellanFindDto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LoginAccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<LoginAccountDto> accounts) {
        this.accounts = accounts;
    }

    public LoginCredentialDto getCredential() {
        return credential;
    }

    public void setCredential(LoginCredentialDto credential) {
        this.credential = credential;
    }

    public List<JoinedMetroDto> getJoinedMetros() {
        return joinedMetros;
    }

    public void setJoinedMetros(List<JoinedMetroDto> joinedMetros) {
        this.joinedMetros = joinedMetros;
    }


    public List<CastellanEmailDto> getEmails() {
        return emails;
    }

    public void setEmails(List<CastellanEmailDto> emails) {
        this.emails = emails;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
