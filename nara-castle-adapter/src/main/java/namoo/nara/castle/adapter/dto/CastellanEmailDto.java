package namoo.nara.castle.adapter.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class CastellanEmailDto implements Serializable {
    //
    private String address;
    private ZonedDateTime createdTime;

    private boolean verified;
    private ZonedDateTime verifiedTime;

    private boolean primary;

    public CastellanEmailDto() {
        //
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public ZonedDateTime getVerifiedTime() {
        return verifiedTime;
    }

    public void setVerifiedTime(ZonedDateTime verifiedTime) {
        this.verifiedTime = verifiedTime;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}
