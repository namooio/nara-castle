package namoo.nara.castle.protocol.sdo;

import java.io.Serializable;

public class CastellanEmailSdo implements Serializable {
    //
    private String address;
    private long createdTime;

    private boolean verified;
    private long verifiedTime;

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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public long getVerifiedTime() {
        return verifiedTime;
    }

    public void setVerifiedTime(long verifiedTime) {
        this.verifiedTime = verifiedTime;
    }

}
