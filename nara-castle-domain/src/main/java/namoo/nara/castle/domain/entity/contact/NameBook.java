package namoo.nara.castle.domain.entity.contact;

import java.util.ArrayList;
import java.util.List;

public class NameBook {
    //
    private List<UserName> nameList = new ArrayList<>();

    public NameBook() {
        //
    }

    public void clear() {
        nameList.clear();
    }

    public void add(UserName name) {
        //
        nameList.add(name);
    }

    public boolean exist(String familyName, String firstName) {
        //
        UserName name = find(familyName, firstName);
        return name != null;

    }

    public boolean existByDisplayName(String displayName) {
        //
        UserName name = findByDisplayName(displayName);
        return name != null;
    }

    public UserName findByDisplayName(String displayName) {
        //
        for(UserName name : nameList) {
            //
            if (name.getDisplayName().equals(displayName)) {
                return name;
            }
        }

        return null;
    }

    public UserName find(String familyName, String firstName) {
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

    public List<UserName> findAll() {
        //
        return nameList;
    }
}
