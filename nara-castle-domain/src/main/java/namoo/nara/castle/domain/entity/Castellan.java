package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.service.data.CastellanCdo;
import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;
import namoo.nara.share.exception.NaraException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Castellan extends Entity implements Aggregate {
    //
    private Set<LoginAccount> accounts;
    private LoginCredential credential;

    private Set<CastellanEmail> emails;

    private List<JoinedMetro> joinedMetros;

    private ZonedDateTime createdTime;

    public Castellan(String id) {
        super(id);
    }

    public static Castellan newInstance(String castleId, CastellanCdo castellanCdo) {
        //
        Castellan castellan = new Castellan(castleId);
        castellan.setAccounts(new HashSet<>());
        castellan.setEmails(new HashSet<>());

        String email = castellanCdo.getEmail();
        castellan.addEmail(email);
        castellan.setPrimaryEmail(email);

        String password = castellanCdo.getPassword();
        castellan.setPasswordCredential(password);

        castellan.setJoinedMetros(new ArrayList<>());
        castellan.setCreatedTime(ZonedDateTime.now());
        return castellan;
    }

    public void addAccount(String loginId, LoginIdType loginIdType) {
        //
        if (existAccount(loginId, loginIdType)) throw new NaraException(String.format("Account[%s:%s] already exist.", loginId, loginIdType));
        LoginAccount account = new LoginAccount();
        account.setLoginId(loginId);
        account.setLoginIdType(loginIdType);
        account.setCreatedTime(ZonedDateTime.now());
        this.accounts.add(account);
    }

    public void removeAccount(String loginId, LoginIdType loginIdType) {
        //
        LoginAccount account = findAccount(loginId, loginIdType);
        if (account != null) {
            this.accounts.remove(account);
        }
    }

    public LoginAccount findAccount(String loginId, LoginIdType loginIdType) {
        //
        for(LoginAccount account : this.accounts) {
            if (loginId.equals(account.getLoginId()) && loginIdType.equals(account.getLoginIdType())) {
                return account;
            }
        }
        return null;
    }

    private boolean existAccount(String loginId, LoginIdType loginIdType) {
        //
        for(LoginAccount account : accounts) {
            if (loginId.equals(account.getLoginId())
                    && loginIdType.equals(account.getLoginIdType())) {
                return true;
            }
        }
        return false;
    }

    public void setPasswordCredential(String password) {
        //
        this.credential = new LoginCredential(password);
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

        LoginAccount account = findAccount(address, LoginIdType.Email);
        if (account != null) {
            this.accounts.remove(account);
        }
    }

    public void verifyEmail(String address) {
        //
        CastellanEmail email = findEmail(address);
        email.verifyEmail();

        // Add verified email as login account.
        addAccount(address, LoginIdType.Email);
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

    public void setPrimaryEmail(String address) {
        //
        CastellanEmail email = findEmail(address);
        if (email == null) throw new NaraException("No email[%s] found to set as primary.");
        for(CastellanEmail castellanEmail : this.emails) {
            castellanEmail.setPrimary(false);
        }
        email.setPrimary(true);
    }

    public CastellanEmail findPrimaryEmail() {
        //
        for(CastellanEmail castellanEmail : this.emails) {
            if (castellanEmail.isPrimary()) {
                return castellanEmail;
            }
        }
        return null;
    }

    public boolean hasPrimaryEmail() {
        //
        CastellanEmail primaryEmail = findPrimaryEmail();
        if (primaryEmail != null) return true;
        return false;
    }

    public String findPrimaryEmailAddress() {
        //
        CastellanEmail primaryEmail = findPrimaryEmail();
        if (primaryEmail != null) primaryEmail.getAddress();
        return null;
    }

    public void addJoinedMetro(String metroId, String citizenId) {
        //
        if (isJoinedMetro(metroId, citizenId)) {
            throw new NaraException(String.format("Already joined metro[%s] as citizen[%s].", metroId, citizenId));
        }
        JoinedMetro joinedMetro = new JoinedMetro();
        joinedMetro.setMetroId(metroId);
        joinedMetro.setCitizenId(citizenId);
        joinedMetro.setJoinedTime(ZonedDateTime.now());
        this.joinedMetros.add(joinedMetro);
    }

    public void removeJoinedMetro(String metroId, String citizenId) {
        //
        JoinedMetro joinedMetro = findJoinedMetro(metroId, citizenId);
        this.joinedMetros.remove(joinedMetro);
    }

    public JoinedMetro findJoinedMetro(String metroId, String citizenId) {
        //
        for(JoinedMetro joinedMetro : this.joinedMetros) {
            if (metroId.equals(joinedMetro.getMetroId()) && citizenId.equals(joinedMetro.getCitizenId())) {
                return joinedMetro;
            }
        }
        return null;
    }

    public boolean isJoinedMetro(String metroId, String citizenId) {
        //
        JoinedMetro joinedMetro = findJoinedMetro(metroId, citizenId);
        if (joinedMetro != null) return true;
        return false;
    }

    public int getJoinedMetrosCount() {
        //
        return this.joinedMetros.size();
    }

    public Set<LoginAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<LoginAccount> accounts) {
        this.accounts = accounts;
    }

    public LoginCredential getCredential() {
        return credential;
    }

    public void setCredential(LoginCredential credential) {
        this.credential = credential;
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

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public static Castellan getSample() {
        //
        CastellanCdo castellanCdo = new CastellanCdo();
        castellanCdo.setEmail("kchuh@nextree.co.kr");
        castellanCdo.setPassword("1234");
        Castellan castellan = Castellan.newInstance("1", castellanCdo);

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
                "accounts=" + accounts +
                ", credential=" + credential +
                ", emails=" + emails +
                ", joinedMetros=" + joinedMetros +
                ", createdTime=" + createdTime +
                '}';
    }

    public static void main(String[] args) {
        //
        Castellan castellan = Castellan.getSample();
        System.out.println(castellan);
    }
}