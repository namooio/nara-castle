package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.share.domain.ValueObject;
import namoo.nara.share.exception.NaraException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Castellan implements ValueObject {
    //
    private Set<CastellanEmail> emails;
    private List<JoinedMetro> joinedMetros;

    public static Castellan newInstance() {
        //
        Castellan castellan = new Castellan();
        castellan.setEmails(new HashSet<>());
        castellan.setJoinedMetros(new ArrayList<>());
        return castellan;
    }

    public void addEmail(String address) {
        //
        if (existEmail(address)) throw new NaraException(String.format("Email[%s] already added.", address));
        CastleContext.getEmailValidator().validate(address);
        CastellanEmail castellanEmail = new CastellanEmail(address);
        castellanEmail.setCreatedTime(ZonedDateTime.now());
        this.emails.add(castellanEmail);
    }

    private boolean existEmail(String address) {
        //
        return findEmail(address) != null;
    }

    public void removeEmail(String address) {
        //
        CastellanEmail email = findEmail(address);
        this.emails.remove(email);
    }

    public void verifyEmail(String address) {
        //
        CastellanEmail email = findEmail(address);
        email.verifyEmail();
    }

    public CastellanEmail findEmail(String address) {
        //
        for(CastellanEmail castellanEmail : this.emails) {
            if (address.equals(castellanEmail.getAddress())) {
                return castellanEmail;
            }
        }
        return null;
    }

    public int getEmailsCount() {
        //
        return this.emails.size();
    }

    public void addJoinedMetro(String metroId, String citizenId) {
        //
        if (isJoinedMetro(metroId)) {
            throw new NaraException(String.format("Already joined metro[%s].", metroId));
        }
        JoinedMetro joinedMetro = new JoinedMetro();
        joinedMetro.setMetroId(metroId);
        joinedMetro.setCitizenId(citizenId);
        joinedMetro.setJoinedTime(ZonedDateTime.now());
        this.joinedMetros.add(joinedMetro);
    }

    public void removeJoinedMetro(String metroId) {
        //
        JoinedMetro joinedMetro = findJoinedMetro(metroId);
        this.joinedMetros.remove(joinedMetro);
    }

    public JoinedMetro findJoinedMetro(String metroId) {
        //
        for(JoinedMetro joinedMetro : this.joinedMetros) {
            if (metroId.equals(joinedMetro.getMetroId())) {
                return joinedMetro;
            }
        }
        return null;
    }

    public boolean isJoinedMetro(String metroId) {
        //
        JoinedMetro joinedMetro = findJoinedMetro(metroId);
        if (joinedMetro != null) return true;
        return false;
    }

    public int getJoinedMetrosCount() {
        //
        return this.joinedMetros.size();
    }

    public Set<CastellanEmail> getEmails() {
        return emails;
    }

    public void setEmails(Set<CastellanEmail> emails) {
        this.emails = emails;
    }

    public List<JoinedMetro> getJoinedMetros() {
        return joinedMetros;
    }

    public void setJoinedMetros(List<JoinedMetro> joinedMetros) {
        this.joinedMetros = joinedMetros;
    }

    public static Castellan getSample() {
        //
        Castellan castellan = Castellan.newInstance();

        castellan.addJoinedMetro("M01", "1@M01");
        castellan.addJoinedMetro("M02", "1@M02");

        castellan.addEmail("michael7557@gmail.com");
        castellan.addEmail("michael7557@naver.com");

        castellan.verifyEmail("kchuh@nextree.co.kr");

        return castellan;
    }

    @Override
    public String toString() {
        return "Castellan{" +
                ", emails=" + emails +
                ", joinedMetros=" + joinedMetros +
                '}';
    }

    public static void main(String[] args) {
        //
        Castellan castellan = Castellan.getSample();
        System.out.println(castellan);
    }
}