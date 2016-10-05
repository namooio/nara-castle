package namoo.nara.castle.da.mongo.document.contact;

import java.util.ArrayList;
import java.util.List;

public class NameBookDoc {
    //
    private List<UserNameDoc> nameList;

    public NameBookDoc() {
        //
    }

    public static NameBookDoc newInstance(NameBook nameBook) {
        //
        NameBookDoc nameBookDoc = new NameBookDoc();
        List<UserName> userNames = nameBook.findAll();
        if (userNames != null) {
            for(UserName userName : userNames) {
                nameBookDoc.addName(UserNameDoc.newInstance(userName));
            }
        }
        return nameBookDoc;
    }

    public NameBook toDomain() {
        //
        NameBook nameBook = new NameBook();
        if (nameList != null) {
            for(UserNameDoc userNameDoc : nameList) {
                nameBook.add(userNameDoc.toDomain());
            }
        }
        return nameBook;
    }

    public void addName(UserNameDoc userName) {
        //
        if(nameList == null) nameList = new ArrayList<>();
        nameList.add(userName);
    }

    public List<UserNameDoc> getNameList() {
        return nameList;
    }

    public void setNameList(List<UserNameDoc> nameList) {
        this.nameList = nameList;
    }
}
