package namoo.nara.castle.adapter.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class CastellanFindDto implements Serializable {
    //
    private String id;
    private String name;
    private String photoId;

    private Set<LoginAccountDto> accounts;
    private LoginCredentialDto credential;

    private Set<CastellanEmailDto> emails;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Set<LoginAccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<LoginAccountDto> accounts) {
        this.accounts = accounts;
    }

    public LoginCredentialDto getCredential() {
        return credential;
    }

    public void setCredential(LoginCredentialDto credential) {
        this.credential = credential;
    }

    public Set<CastellanEmailDto> getEmails() {
        return emails;
    }

    public void setEmails(Set<CastellanEmailDto> emails) {
        this.emails = emails;
    }

    public List<JoinedMetroDto> getJoinedMetros() {
        return joinedMetros;
    }

    public void setJoinedMetros(List<JoinedMetroDto> joinedMetros) {
        this.joinedMetros = joinedMetros;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
