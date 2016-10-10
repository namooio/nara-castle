package namoo.nara.castle.protocol.sdo;

import java.io.Serializable;
import java.util.List;

public class CastellanFindSdo implements Serializable {
    //
    private String id;

    private List<LoginAccountSdo> accounts;
    private LoginCredentialSdo credential;

    private List<CastellanEmailSdo> emails;

    private List<JoinedMetroSdo> joinedMetros;

    private long createdTime;

    public CastellanFindSdo() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LoginAccountSdo> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<LoginAccountSdo> accounts) {
        this.accounts = accounts;
    }

    public LoginCredentialSdo getCredential() {
        return credential;
    }

    public void setCredential(LoginCredentialSdo credential) {
        this.credential = credential;
    }

    public List<JoinedMetroSdo> getJoinedMetros() {
        return joinedMetros;
    }

    public void setJoinedMetros(List<JoinedMetroSdo> joinedMetros) {
        this.joinedMetros = joinedMetros;
    }


    public List<CastellanEmailSdo> getEmails() {
        return emails;
    }

    public void setEmails(List<CastellanEmailSdo> emails) {
        this.emails = emails;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
