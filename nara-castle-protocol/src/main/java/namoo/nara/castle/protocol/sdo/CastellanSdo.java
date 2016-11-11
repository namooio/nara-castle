package namoo.nara.castle.protocol.sdo;

import java.io.Serializable;
import java.util.List;

public class CastellanSdo implements Serializable {
    //
    private List<CastellanEmailSdo> emails;
    private List<JoinedMetroSdo> joinedMetros;

    public CastellanSdo() {

    }

    public List<JoinedMetroSdo> getJoinedMetros() {
        return joinedMetros;
    }

    public void setJoinedMetros(List<JoinedMetroSdo> joinedMetros) {
        this.joinedMetros = joinedMetros;
    }

    public List<CastellanEmailSdo> getEmails() {
        return emails;
    }

    public void setEmails(List<CastellanEmailSdo> emails) {
        this.emails = emails;
    }
}
