package namoo.nara.castle.adapter.dto;

import java.io.Serializable;

public class CastellanCreationDto implements Serializable {

    private String name;

    public CastellanCreationDto() {
        //
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
