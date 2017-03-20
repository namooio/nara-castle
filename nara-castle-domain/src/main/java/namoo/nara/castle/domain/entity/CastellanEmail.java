package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.ValueObject;

import java.time.Instant;

public class CastellanEmail implements ValueObject {
    //
    private String address;
    private Instant createdTime;

    public CastellanEmail() {
        this.createdTime = Instant.now();
    }

    public CastellanEmail(String address) {
        this();
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("address:'").append(address).append('\'');
        sb.append(", createdTime:").append(createdTime);
        sb.append('}');
        return sb.toString();
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

}
