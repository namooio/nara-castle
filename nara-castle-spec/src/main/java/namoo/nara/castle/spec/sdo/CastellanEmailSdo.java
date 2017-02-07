package namoo.nara.castle.spec.sdo;

import java.io.Serializable;

public class CastellanEmailSdo implements Serializable {
    //
    private String address;
    private long createdTime;

    public CastellanEmailSdo() {
        //
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

}
