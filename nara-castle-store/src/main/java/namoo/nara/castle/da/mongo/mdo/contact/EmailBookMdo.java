package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.UserEmail;

import java.util.ArrayList;
import java.util.List;

public class EmailBookMdo {
    //
    private List<UserEmail> emailList = new ArrayList<>();

    public EmailBookMdo() {
        //
    }

    public void addEmail(UserEmail email) {
        //
        emailList.add(email);
    }

    public boolean existEmail(String email) {
        //
         if (findEmail(email) != null) {
            return true;
        }

        return false;
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
}