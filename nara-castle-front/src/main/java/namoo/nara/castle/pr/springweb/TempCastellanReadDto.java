package namoo.nara.castle.pr.springweb;

/**
 * Created by hkkang on 2016-04-12.
 */
public class TempCastellanReadDto {

    private String id;

    private String displayName;

    private String primaryEmail;


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }
}
