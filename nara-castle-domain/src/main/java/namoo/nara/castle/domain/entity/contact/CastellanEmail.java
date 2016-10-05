package namoo.nara.castle.domain.entity.contact;

import namoo.nara.share.domain.ValueObject;

import java.time.ZonedDateTime;

public class CastellanEmail implements ValueObject {
    //
    private String address;
    private ZonedDateTime registeredTime;

    private boolean verified;
    private ZonedDateTime verifiedTime;

    public CastellanEmail(String address) {
        this.address = address;
    }

    public void verifyEmail() {
        //
        this.verified = true;
        this.verifiedTime = ZonedDateTime.now();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ZonedDateTime getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(ZonedDateTime registeredTime) {
        this.registeredTime = registeredTime;
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

    @Override
    public String toString() {
        return "CastellanEmail{" +
                "address='" + address + '\'' +
                ", registeredTime=" + registeredTime +
                ", verified=" + verified +
                ", verifiedTime=" + verifiedTime +
                '}';
    }
}
