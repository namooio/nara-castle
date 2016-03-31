package namoo.nara.castle.domain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 1. 29..
 */
public class Castellan {

    private String oid;

    private String displayName;

    private List<CastellanEmail> emails;

    private List<CastellanName> names;

    public Castellan() {
    }

    public Castellan(String oid) {
        this.oid = oid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<CastellanEmail> getEmails() {
        return emails;
    }

    public void setEmails(List<CastellanEmail> emails) {
        this.emails = emails;
    }

    public List<CastellanName> getNames() {
        return names;
    }

    public void setNames(List<CastellanName> names) {
        this.names = names;
    }

    public void addEmail(CastellanEmail email) {
        if (this.emails == null) this.emails = new ArrayList<>();
        this.emails.add(email);
    }

    public void addName(CastellanName name) {
        if (this.names == null) this.names = new ArrayList<>();
        this.names.add(name);
    }

}
