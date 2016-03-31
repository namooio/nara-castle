package namoo.nara.castle.remote.dto;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
public class CastellanCreateDto {

    private String id;
    private String email;

    public CastellanCreateDto() {
    }

    public CastellanCreateDto(String id) {
        this.id = id;
    }

    public CastellanCreateDto(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
