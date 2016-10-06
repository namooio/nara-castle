package namoo.nara.castle.adapter.dto;

import java.io.Serializable;

public class CastellanModificationDto implements Serializable {
    //
    private String name;

    public CastellanModificationDto() {
        //
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
