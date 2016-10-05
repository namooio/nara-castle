package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.Aggregate;
import namoo.nara.share.domain.Entity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Castellan extends Entity implements Aggregate {
    //
    private String name;
    private String photoId;         // profile photo id 

    private Set<LoginAccount> loginAccounts;
    private LoginCredential credential;

    private Set<CastellanEmail> emails;

    private List<JoinedMetro> joinedMetros;

    private ZonedDateTime createdTime;

    public Castellan(String id) {
        super(id);
    }

    public static Castellan newInstance(String castleId, String name) {
        //
        Castellan castellan = new Castellan(castleId);
        castellan.setName(name);
        castellan.setLoginAccounts(new HashSet<>());
        castellan.setEmails(new HashSet<>());
        castellan.setJoinedMetros(new ArrayList<>());
        castellan.setCreatedTime(ZonedDateTime.now());
        return castellan;
    }

    public void addAccount(String loginId, LoginIdType loginIdType) {
        //
        LoginAccount loginAccount = new LoginAccount();
        loginAccount.setLoginId(loginId);
        loginAccount.setLoginIdType(loginIdType);
        loginAccount.setCreatedTime(ZonedDateTime.now());
        this.loginAccounts.add(loginAccount);
    }

    public void removeAccount(String loginId, LoginIdType loginIdType) {
        //
        LoginAccount loginAccount = findAccount(loginId, loginIdType);
        if (loginAccount != null) {
            this.loginAccounts.remove(loginAccount);
        }
    }

    public LoginAccount findAccount(String loginId, LoginIdType loginIdType) {
        //
        for(LoginAccount loginAccount : this.loginAccounts) {
            if (loginId.equals(loginAccount.getLoginId()) && loginIdType.equals(loginAccount.getLoginIdType())) {
                return loginAccount;
            }
        }
        return null;
    }

    public boolean hasAccount(LoginAccount newAccount) {
        //
        for(LoginAccount account : loginAccounts) {
            if (newAccount.getLoginId().equals(account.getLoginId())
                    && newAccount.getLoginIdType().equals(account.getLoginIdType())) {
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
        CastellanEmail castellanEmail = new CastellanEmail(address);
        castellanEmail.setCreatedTime(ZonedDateTime.now());
        this.emails.add(castellanEmail);
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

    public int getJoinedMetrosCount() {
        //
        return this.joinedMetros.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Set<LoginAccount> getLoginAccounts() {
        return loginAccounts;
    }

    public void setLoginAccounts(Set<LoginAccount> loginAccounts) {
        this.loginAccounts = loginAccounts;
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
        Castellan castellan = Castellan.newInstance("1", "kchuh");
        castellan.addAccount("kchuh@nextree.co.kr", LoginIdType.Email);
        castellan.addAccount("kchuh", LoginIdType.Username);

        castellan.addJoinedMetro("M01", "1@M01");
        castellan.addJoinedMetro("M02", "1@M02");

        castellan.addEmail("kchuh@nextree.co.kr");
        castellan.addEmail("michael7557@gmail.com");
        castellan.addEmail("michael7557@naver.com");

        castellan.verifyEmail("kchuh@nextree.co.kr");

        return castellan;
    }

    @Override
    public String toString() {
        return "Castellan{" +
                "name='" + name + '\'' +
                ", photoId='" + photoId + '\'' +
                ", loginAccounts=" + loginAccounts +
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