package namoo.nara.castle.remote.dto;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 17..
 */
public class CastellanReadDto {

    private String id;

    private String displayName;

    private String primaryEmail;

    public CastellanReadDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }
}
