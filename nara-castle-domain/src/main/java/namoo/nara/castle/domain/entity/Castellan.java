package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.ValueObject;
import namoo.nara.share.domain.granule.Email;
import namoo.nara.share.domain.granule.EmailList;
import namoo.nara.share.exception.NaraException;
import namoo.nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class Castellan implements ValueObject {
    //
    /**
     * Emails are globally unique.
     */
    private EmailList emails;
    private List<JoinedMetro> joinedMetros;

    public Castellan() {
        //
        emails = new EmailList();
        joinedMetros = new ArrayList<>();
    }

    public Castellan(Email email) {
        //
        this();
        emails.add(email);
    }

    public Castellan(JoinedMetro joinedMetro) {
        //
        this();
        joinedMetros.add(joinedMetro);
    }

    public Castellan(Email email, JoinedMetro joinedMetro) {
        //
        this();
        emails.add(email);
        joinedMetros.add(joinedMetro);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("emails:").append(emails);
        sb.append(", joinedMetros:").append(joinedMetros);
        sb.append('}');
        return sb.toString();
    }

    public void addJoinedMetro(String nationId, String metroId, String civilianId) {
        //
        JoinedMetro joinedMetro = new JoinedMetro(nationId, metroId, civilianId);
        addJoinedMetro(joinedMetro);
    }

    public void addJoinedMetro(JoinedMetro joinedMetro) {
        //
        if (isJoinedMetro(joinedMetro.getNationId(), joinedMetro.getMetroId())) throw new NaraException(String.format("Already joined metro[%s].", joinedMetro.getMetroId()));
        joinedMetro.setJoinedTime(System.currentTimeMillis());
        this.joinedMetros.add(joinedMetro);
    }

    public void removeJoinedMetro(String nationId, String metroId) {
        //
        JoinedMetro joinedMetro = findJoinedMetro(nationId, metroId);
        this.joinedMetros.remove(joinedMetro);
    }

    public JoinedMetro findJoinedMetro(String nationId, String metroId) {
        //
        return this.joinedMetros
                .stream()
                .filter(joinedMetro -> nationId.equals(joinedMetro.getNationId()) && metroId.equals(joinedMetro.getMetroId()))
                .findFirst()
                .orElse(null);
    }

    public boolean isJoinedMetro(String nationId, String metroId) {
        //
        return findJoinedMetro(nationId, metroId) != null;
    }

    public boolean hasEmails() {
        return emails.size() > 0;
    }

    public int getJoinedMetroCount() {
        return this.joinedMetros.size();
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public static Castellan fromJson(String json) {
        return JsonUtil.fromJson(json, Castellan.class);
    }

    public EmailList getEmails() {
        return emails;
    }

    public void setEmails(EmailList emails) {
        this.emails = emails;
    }

    public List<JoinedMetro> getJoinedMetros() {
        return joinedMetros;
    }

    public void setJoinedMetros(List<JoinedMetro> joinedMetros) {
        this.joinedMetros = joinedMetros;
    }

}