package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.NameBook;
import namoo.nara.castle.domain.entity.contact.UserName;

import java.util.ArrayList;
import java.util.List;

public class NameBookMdo {
    //
    private List<UserNameMdo> nameMdoList;

    public NameBookMdo() {
        //
    }

    public static NameBookMdo newInstance(NameBook nameBook) {
        //
        NameBookMdo nameBookMdo = new NameBookMdo();
        List<UserName> userNames = nameBook.findAll();
        if (userNames != null) {
            for(UserName userName : userNames) {
                nameBookMdo.addNameMdo(UserNameMdo.newInstance(userName));
            }
        }
        return nameBookMdo;
    }

    public NameBook getDomain() {
        //
        NameBook nameBook = new NameBook();
        if (nameMdoList != null) {
            for(UserNameMdo userNameMdo : nameMdoList) {
                nameBook.add(userNameMdo.getDomain());
            }
        }
        return nameBook;
    }

    public void addNameMdo(UserNameMdo userNameMdo) {
        //
        if(nameMdoList == null) nameMdoList = new ArrayList<>();
        nameMdoList.add(userNameMdo);
    }

    public List<UserNameMdo> getNameMdoList() {
        return nameMdoList;
    }

    public void setNameMdoList(List<UserNameMdo> nameMdoList) {
        this.nameMdoList = nameMdoList;
    }
}
