package namoo.nara.castle.domain.entity.contact;

import namoo.nara.share.exception.NaraException;

import java.util.ArrayList;
import java.util.List;

public class EmailBook {
    //
    private List<UserEmail> emailList = new ArrayList<>();

    public EmailBook() {
        //
    }

    public void clear() {
        //
        emailList.clear();
    }

    public void addEmail(UserEmail email) {
        //
        for(UserEmail userEmail : emailList) {
            if (email.getEmail().equals(userEmail.getEmail())) throw new NaraException(String.format("Email[%s] already exists.", email));
        }
        emailList.add(email);
    }

    public boolean existEmail(String email) {
        //
        return findEmail(email) != null;
    }

    public void removeEmail(String email) {
        //
        UserEmail userEmail = findEmail(email);
        if (userEmail == null) throw  new NaraException(String.format("Email[%s] not found to remove.", email));
        this.emailList.remove(userEmail);
    }

    public UserEmail findEmail(String email) {
        //
        for(UserEmail userEmail : emailList) {
            //
            if (userEmail.getEmail().equals(email)) {
                return userEmail;
            }
        }

        return null;
    }

    public List<UserEmail> findAll() {
        //
        return emailList;
    }

    public void verifyEmail(String email) {
        //
        UserEmail userEmail = findEmail(email);
        if (userEmail == null) throw  new NaraException(String.format("Email[%s] not found for verification.", email));
        userEmail.verify();
    }
}