package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.ValueObject;

public class CastellanEmail implements ValueObject {

    private String address;
    private Long createdTime;

    public CastellanEmail() {

    }

    public CastellanEmail(String address) {
        this.createdTime = System.currentTimeMillis();
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

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

}
