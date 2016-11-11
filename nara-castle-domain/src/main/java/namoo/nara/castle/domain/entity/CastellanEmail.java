package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.ValueObject;

import java.time.ZonedDateTime;

public class CastellanEmail implements ValueObject {
    //
    private String address;
    private ZonedDateTime createdTime;

    public CastellanEmail(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("address:'").append(address).append('\'');
        sb.append(", createdTime:").append(createdTime);
        sb.append('}');
        return sb.toString();
    }
}
