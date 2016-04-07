package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.UserName;

import java.util.ArrayList;
import java.util.List;

public class NameBookMdo {
    //
    private List<UserName> nameList = new ArrayList<>();

    public NameBookMdo() {
        //
    }

    public void addName(UserName name) {
        //
        nameList.add(name);
    }

    public boolean existName(String familyName, String firstName) {
        //
        UserName name = findName(familyName, firstName);
        if (name != null) {
            return true;
        }

        return false;
    }

    public boolean existNameByDisplayName(String displayName) {
        //
        UserName name = findNameByDisplayName(displayName);
        if (name != null) {
            return true;
        }

        return false;
    }

    public UserName findNameByDisplayName(String displayName) {
        //
        for(UserName name : nameList) {
            //
            if (name.getDisplayName().equals(displayName)) {
                return name;
            }
        }

        return null;
    }

    public UserName findName(String familyName, String firstName) {
        //
        for(UserName name : nameList) {
            //
            if (name.getFamilyName().equals(familyName) &&
                    name.getFirstName().equals(firstName)) {
                return name;
            }
        }

        return null;
    }
}
