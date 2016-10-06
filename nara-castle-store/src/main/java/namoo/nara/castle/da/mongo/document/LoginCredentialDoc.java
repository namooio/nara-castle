package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.domain.entity.LoginCredential;

public class LoginCredentialDoc {

    private String password;

    public LoginCredentialDoc() {
        //
    }

    public static LoginCredentialDoc toDocument(LoginCredential domain) {
        //
        if (domain == null) return null;
        LoginCredentialDoc doc = new LoginCredentialDoc();
        doc.setPassword(domain.getPassword());
        return doc;
    }

    public LoginCredential toDomain() {
        //
        LoginCredential domain = new LoginCredential();
        domain.setPassword(password);
        return domain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
