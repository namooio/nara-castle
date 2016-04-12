package namoo.nara.castle.domain.entity.contact;

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
        emailList.add(email);
    }

    public boolean existEmail(String email) {
        //
        return findEmail(email) != null;
    }

    public void removeEmail(UserEmail email) {
        //
        this.emailList.remove(email);
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
}